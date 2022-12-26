package ClasesPrincipales;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.ErrorManager;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/*
 * Enlaces relacionados
 * 
 * Logger con colores diferenciados de error
 * https://stackoverflow.com/questions/194165/how-do-i-change-java-logging-console-output-from-std-err-to-std-out
 * 
 * Escribir en fichero
 * https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
 * 
 * Cambiar formato del log de salida
 * https://www.informit.com/articles/article.aspx?p=27774&seqNum=7
 * 
 * 
 * NIVELES LOGGER
 * SEVERE: Nivel de mensaje indicando un error serio.
 * WARNING: Indica un error potencial.
 * INFO: Para mensajes informativos.
 * CONFIG: Usado con mensajes relacionados con la configuración.
 * FINE: Proporciona información de traza de la ejecución.
 * FINER: Información de traza más detallada.
 * FINEST: Información de traza muy detallada.
 *
 */

public class GestorLog {
	private static String logFilePath = "./log/default.log";
	private static FileInputStream logProp = null;
	private static Handler logHandler = new Handler() {
        public void publish(LogRecord record) {
            if (getFormatter() == null) {
            	SimpleFormatter sf = new SimpleFormatter();
                setFormatter(sf);    			
            }
            try {
            	Formatter formater = getFormatter();
                String message = getFormatter().format(record);
                
                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    System.err.write(message.getBytes());  
                }
                else {
                    System.out.write(message.getBytes());
                }
                Files.write(Paths.get(logFilePath), message.getBytes(), StandardOpenOption.APPEND);

            } catch (NoSuchFileException e) { 
            	System.out.println("crea el fichero default.log");
            }
            catch (Exception exception) {
                reportError(null, exception, ErrorManager.FORMAT_FAILURE);
            } 
        }
        public void close() throws SecurityException {}
        public void flush(){}
    };
    public static void info(String text) {
    	GestorLog.log(Level.INFO, text);
    }
    public static void warning(String text) {
    	GestorLog.log(Level.WARNING, text);
    }
    public static void fine(String text) {
    	GestorLog.log(Level.FINE, text);
    }
	private static void log(Level level,String text) {
		try {
			if (logProp==null) {
				logProp = new FileInputStream("logger.properties");
				LogManager.getLogManager().readConfiguration(logProp);
			}
		} catch (Exception e) {
			System.out.println("crea el fichero logger.properties");
			e.printStackTrace();
		}
		LogRecord r = new LogRecord(level,text);
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String className = stackTraceElements[3].getClassName();
		String mName = stackTraceElements[3].getMethodName();
	    r.setSourceClassName(className);
	    r.setSourceMethodName(mName);
	    logHandler.publish(r);
	}

}
