import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {

	static final String WATCH_PATH1 = "D:\\dev\\java\\workspace\\FileOperationWatcher\\testdata1";
	static final String WATCH_PATH2 = "D:\\dev\\java\\workspace\\FileOperationWatcher\\testdata2";

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);

		FileOperationWatcher watcher1 = new FileOperationWatcher(new FileOperationListerner(), WATCH_PATH1, "");
		FileOperationWatcher watcher2 = new FileOperationWatcher(new FileOperationListerner(), WATCH_PATH2, "target.txt");

		executor.submit(watcher1);
		executor.submit(watcher2);
	}
}
