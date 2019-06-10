
public class FileOperationListerner {

	String targetFilename;

	public FileOperationListerner() {}

	public void onChange(String filename) {
		System.out.println("change detected: " + filename);
//		WatchFileMapper.getInstance()
//		.getWatchFileMap()
//		.entrySet()
//		.stream()
//		.forEach(entry -> {
//			System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
//		});
	}
}