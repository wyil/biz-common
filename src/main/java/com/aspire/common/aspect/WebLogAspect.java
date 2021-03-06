package com.aspire.common.aspect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aspire.common.service.AccountService;
import com.aspire.common.util.WebUtil;
import com.aspire.common.vo.StaffVo;
import com.aspire.webbas.common.sessionuser.api.SessionUserApi;
import com.aspire.webbas.support.log.api.LogApi;
import com.aspire.webbas.support.log.api.bo.LogBo;
import com.aspire.webbas.support.log.core.entity.LogType;

/**
 * @ClassName: OperationLogAspect
 * @Description:web系统操作日志切面
 * @author huangruiwen
 * @date 2019年3月4日
 */
@Aspect
@Component
public class WebLogAspect {
	private static Logger log = LoggerFactory.getLogger(WebLogAspect.class);
	ExpressionParser parser = new SpelExpressionParser();
	LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

	@Resource
	private AccountService accountUtil;
	@Autowired
	private LogApi logApi;
	@Autowired
	private com.aspire.webbas.support.log.core.service.LogService LogService;

	@Autowired
	private SessionUserApi sessionUserApi;

	@Value("${operation.log.on}")
	public boolean logOn;

	/**
	 * 统计方法执行耗时Around环绕通知
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */

	@Around("@annotation(com.aspire.common.aspect.WebLogAnnotation)")
	public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		// 定义返回对象、得到方法需要的参数
		Object obj = null;
		Object[] args = joinPoint.getArgs();
		long startTime = System.currentTimeMillis();
		String logId = UUID.randomUUID().toString();
		log.debug("log={},开始执行时间：{}", logId, startTime);

		String operationResult = "成功";
		try {
			obj = joinPoint.proceed(args);
		} catch (RuntimeException e) {
			operationResult = "fail";
			log.error("统计某方法执行耗时环绕通知出错", e);
			// 把Action业务异常抛出
			throw e;
		} catch (Throwable e) {
			operationResult = "fail";
			throw e;
		}

		// 获取执行的方法名
		long endTime = System.currentTimeMillis();
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		String methodName = signature.toShortString();
		// 打印耗时的信息
		long diffTime = endTime - startTime;
		log.debug("log={},执行耗时：{} ms，执行方法名：{}", logId, diffTime, methodName);
		try {
			if (logOn) {
				businessLog(joinPoint, operationResult);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);

		}
		return obj;
	}

	private void businessLog(ProceedingJoinPoint joinPoint, String operationResult) throws Exception {
		MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
		Field proxy = methodPoint.getClass().getDeclaredField("methodInvocation");
		proxy.setAccessible(true);
		ReflectiveMethodInvocation j = (ReflectiveMethodInvocation) proxy.get(methodPoint);
		Method method = j.getMethod();
		WebLogAnnotation webLogAnno = method.getAnnotation(WebLogAnnotation.class);

		Object[] args = joinPoint.getArgs();
		String[] params = discoverer.getParameterNames(method);
		EvaluationContext context = new StandardEvaluationContext();
		for (int len = 0; len < params.length; len++) {
			context.setVariable(params[len], args[len]);
		}
		String dataId = null;

		if (StringUtils.isNotEmpty(webLogAnno.spelDataId())) {
			Expression expression = parser.parseExpression(webLogAnno.spelDataId());
			Object valueObj = expression.getValue(context);
			if (valueObj != null) {
				dataId = valueObj.toString();
			}
		}
		log.debug("logType:{}", webLogAnno.webLogTypeEnum().getLogType());
		log.debug("businessName:{}", webLogAnno.webLogTypeEnum().getBusinessName());
		log.debug("action:{}", webLogAnno.webLogTypeEnum().getAction());
		log.debug("spelDataId:{}={}", webLogAnno.spelDataId(), dataId);

		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String ip = sessionUserApi.getSessionUser().getIp();
		if (StringUtils.isEmpty(ip)) {
			ip = WebUtil.getCliectIp(request);
		}

		// 记录下请求内容
		log.debug("URL : {}", request.getRequestURL());
		log.debug("HTTP_METHOD : {}", request.getMethod());
		log.debug("IP : {}", ip);
		StringBuilder paramjson = new StringBuilder();
		StringBuilder paramAll = new StringBuilder();
		paramjson.append("{");

		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			if (paramAll.length() > 2) {
				paramAll.append(",");
			}
			paramAll.append("\"" + name + "\":\"" + request.getParameter(name) + "\"");
			if (paramjson.length() < 1500 && !("ticket".equals(name) || "roleType".equals(name) || "domain".equals(name)
					|| "a".equals(name))) {
				if (paramjson.length() > 2) {
					paramjson.append(",");
				}
				paramjson.append("\"" + name + "\":\"" + request.getParameter(name) + "\"");
			}

		}
		paramjson.append("}");
		log.debug("paramAll : {}", paramAll);
		log.debug("paramjson : {}", paramjson);
		if (!"待设置".equals(webLogAnno.webLogTypeEnum().getLogType())) {
			insertBusinessLog(operationResult, webLogAnno, dataId, request, ip, paramjson);
		}
	}

	/**
	 * 插入日志
	 * 
	 * @param operationResult
	 * @param webLogAnno
	 * @param dataId
	 * @param request
	 * @param ip
	 * @param paramjson
	 * @throws Exception
	 */
	private void insertBusinessLog(String operationResult, WebLogAnnotation webLogAnno, String dataId,
			HttpServletRequest request, String ip, StringBuilder paramjson) throws Exception {
		String logType = webLogAnno.webLogTypeEnum().getLogType();

		// 被操作实体ID
		String businessId = dataId;
		if (StringUtils.isEmpty(businessId)) {
			businessId = webLogAnno.webLogTypeEnum().getAction();
		}
		// 被操作实体名称?
		String businessName = webLogAnno.webLogTypeEnum().getBusinessName();
		String action = webLogAnno.webLogTypeEnum().getAction();
		Map<String, String> logInfoMap = new HashMap<>(6);
		logInfoMap.put("operation", webLogAnno.webLogTypeEnum().getAction());
		if (paramjson.length() > 350) {
			logInfoMap.put("parameter", paramjson.substring(0, 350) + "...");
		} else {
			logInfoMap.put("parameter", paramjson.toString());
		}
		StaffVo staff = accountUtil.getUpcUser();
		String operatorId = "";
		String operatorName = "";
		if (staff != null) {
			operatorId = staff.getLoginName();
			operatorName = staff.getRealName();
		} else {
			operatorId = (String) request.getSession().getAttribute("LOGIN_MOBILE");
		}
		logInfoMap.put("clientIp", ip);
		logInfoMap.put("result", operationResult);
		logInfoMap.put("operatorId", operatorId);
		logInfoMap.put("operatorName", operatorName);

		logInfoMap.put("businessId", businessId);
		logInfoMap.put("operatorAction", action);
		logInfoMap.put("operatorMenu", businessName);

		LogBo logBo = new LogBo();
		logBo.setBusinessId(businessId);
		logBo.setBusinessName(businessName);
		// 被操作实体类型?
		LogType findLogType = LogService.findLogType(logType);
		String businessType = findLogType.getParentType();
		logBo.setBusinessType(businessType);
		logBo.setClientIp(ip);
		logBo.setLogType(logType);
		logBo.setOperationDomain("admin");
		logBo.setOperationResult(operationResult);
		logBo.setOperatorId(operatorId);
		logBo.setOperatorName(operatorName);
		logBo.setOtherInfoMap(logInfoMap);
		logBo.setOrganizationId(staff.getOrgId());
		logBo.setDivisionId(staff.getDeptName());
		logApi.insert(logBo);
	}
}
