package com.eling.elcms.agent.model;

/**
 * 存储operation的参数。
 * @author leo_soul
 *
 */
public class OperationConfig {
	/** 访问wsdl的url */
	private String wsdlAddr;
	//接口级@WebService
	/** targetNamespace属性（对应服务端@WebService的targetNamespace） */
	private String targetNamespace;
	/** wsdl:service标签的name属性（对应服务端@WebService的serviceName） */
	private String serviceName;
	/** wsdl:portType标签的name属性（对应服务端@WebService的portName） */
	private String portName;
	//方法级@WebMethod
	/** wsdl:operation标签的name属性（对应服务端@WebMethod的operationName） */
	private String operationName;
	//---------------getter setter---------------
	public String getWsdlAddr() {
		return wsdlAddr;
	}
	public void setWsdlAddr(String wsdlAddr) {
		this.wsdlAddr = wsdlAddr;
	}
	public String getTargetNamespace() {
		return targetNamespace;
	}
	public void setTargetNamespace(String targetNamespace) {
		this.targetNamespace = targetNamespace;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getPortName() {
		return portName;
	}
	public void setPortName(String portName) {
		this.portName = portName;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
