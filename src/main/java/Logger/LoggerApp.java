package Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class LoggerApp {

//    private static final Logger LOGGER = LogManager.getLogger(LoggerApp.class.getName());

    public static void main(String[] args) {
//        LOGGER.trace("trace");
//        LOGGER.debug("debug");
//        LOGGER.info("info");
//        LOGGER.warn("warn");
//        LOGGER.error("error");
//        LOGGER.fatal("fatal");

        maxK();

    }

    public static List<String> maxK() {
        List<String> list = new LinkedList<>();
        int maxSize = 7;

        for (int i = 0; i < 21; i++) {
            String s = String.valueOf(i);
            list.add(s);
            System.out.println(list);
            if (list.size() > maxSize) {
                list.remove(0);
            }
            System.out.println(list);
//            LOGGER.debug("List is {} for i {}", list, i);

            }
        return list;
        }

}

