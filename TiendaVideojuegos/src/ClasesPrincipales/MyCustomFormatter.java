package ClasesPrincipales;
import java.util.logging.*;
import java.util.Date;

// This custom formatter formats the logs into HTML format
// Each record is logged to a single line

class MyCustomFormatter extends Formatter {

  // This method is called for every log record

  public String format(LogRecord rec) {

   StringBuffer sb = new StringBuffer(1000);

   // give a red color to any messages with levels >= WARNING
   if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
        sb.append("<font color=\"red\">");
        sb.append(rec.getLevel());
        sb.append("</font>");
      } else {
        sb.append(rec.getLevel());
      }
      sb.append(' ');
      sb.append(formatMessage(rec));
      sb.append('\n');
      return sb.toString();
    }

    // This method is called when the handler is created

    public String getHead(Handler h) {
      return "<HTML><HEAD> My Custom Log from "+(new Date())+"</HEAD><BODY><H1>The logs</H1><PRE>\n";
    }

    // This method is called when the handler is closed

    public String getTail(Handler h) {
      return "</PRE></BODY></HTML>\n";
    }
  }