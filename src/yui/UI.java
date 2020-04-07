package yui;

public interface UI {
	public void showMessage(String message);
	public void setStage(MenuItem[] items);
	public void removeItem(MenuItem item);
	public void appendItem(MenuItem item);
	public void prependItem(MenuItem item);
	public void close();
}
