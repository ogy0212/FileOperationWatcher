import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public class WatchFileMapper {

	@Getter
	private Map<String, String> watchFileMap;

	private static final WatchFileMapper INSTANCE = new WatchFileMapper();

	private WatchFileMapper() {
		this.watchFileMap = new HashMap<>();
	}

	public static WatchFileMapper getInstance() {
		return INSTANCE;
	}
}
