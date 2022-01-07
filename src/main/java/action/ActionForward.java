package action;

public class ActionForward{
	
	private String path = null;
	private String errorMsg = null;
	private String msg = null;
	private boolean isRedirectToAction = false;
	
	public ActionForward() {}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isRedirectToAction() {
		return isRedirectToAction;
	}

	public void setRedirectToAction(boolean isRedirectToAction) {
		this.isRedirectToAction = isRedirectToAction;
	}

	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", errorMsg=" + errorMsg + ", msg=" + msg + ", isRedirectToAction="
				+ isRedirectToAction + "]";
	}

}
