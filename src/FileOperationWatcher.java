import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;

public class FileOperationWatcher implements Runnable {

	public String dirPath;
	public FileOperationListerner listener;
	public String targetFilename;

	public FileOperationWatcher(FileOperationListerner listener, String path, String targetFilename) {
		this.dirPath = path;
		this.listener = listener;
		this.targetFilename = targetFilename;
	}

	@Override
	public void run() {
		WatchService watcher;
		try {
			watcher = FileSystems.getDefault().newWatchService();

			Watchable path = Paths.get(this.dirPath);
			path.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			WatchKey watchKey;
			try {
				watchKey = watcher.take();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
				return;
			}

			try {
				Thread.sleep( 50 );
			} catch (InterruptedException e) {
				// https://stackoverflow.com/questions/16777869/java-7-watchservice-ignoring-multiple-occurrences-of-the-same-event
				e.printStackTrace();
			}

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				Kind<?> kind = event.kind();
				Object context = event.context();
				if (this.targetFilename.isEmpty() || this.targetFilename.equals(context.toString())) {
					this.listener.onChange(context.toString());
				} else {
					System.out.println("not watched");
				}
				System.out.println("kind=" + kind + ", context=" + context);
			}

			if (!watchKey.reset()) {
				System.out.println("WatchKey が無効になりました");
				return;
			}
		}
	}
}
