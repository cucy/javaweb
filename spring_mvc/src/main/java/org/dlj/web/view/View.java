package org.dlj.web.view;

/**
 * 视图模型
 * @author zhxg
 *
 */
public class View {

	private String url; // 跳转路径
	
	private String dispathAction = DispatchActionConstant.FORWARD; // 跳转方式
	
	public View(String url) {
		this.url = url;
	}
	
	public View(String url, String name, Object value) {
		this.url = url;
		ViewData view = new ViewData();
		view.put(name, value);
	}
	
	public View(String url, String name, String dispathAction, Object value) {
		this.dispathAction = dispathAction;
		this.url = url;
		ViewData view = new ViewData();
		view.put(name, value);
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDispathAction() {
		return dispathAction;
	}
	
	public void setDispathAction(String dispathAction) {
		this.dispathAction = dispathAction;
	}
}
