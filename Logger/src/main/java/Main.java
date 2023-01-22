import com.services.logger.enums.LogLevel;
import com.services.logger.enums.LoggerType;
import com.services.logger.impl.LoggerImpl;
import com.services.logger.impl.WarningLoggerImpl;
import com.services.logger.model.LoggingProps;
import com.services.logger.singleton.LoggerFactory;
import lombok.val;

public class Main {
    public static void main(String[] args) {
        val logger = LoggerFactory.getLogger(LoggerType.SYSTEM_LOGGER);
        val loggingProps = new LoggingProps(LogLevel.ERROR);
        val log = new LoggerImpl(logger, loggingProps);
        log.info("This is an INFO log!");
        log.debug("This is a DEBUG log!");
        log.warning("This is a WARNING log");
        log.error("This is an ERROR log");

        val customLogger = new WarningLoggerImpl(logger, loggingProps);
        customLogger.error("This is a custom ERROR log");
        customLogger.warning("This is a custom WARNING log");
    }
}
