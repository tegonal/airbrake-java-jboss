package airbrake;

import org.jboss.logmanager.ExtHandler;
import org.jboss.logmanager.ExtLogRecord;
import org.jboss.logmanager.Level;

public class JBossAirbrakeHandler extends ExtHandler {

  private final AirbrakeNotifier airbrakeNotifier = new AirbrakeNotifier();

  private String m_apikey;
  private String m_env;

  public JBossAirbrakeHandler() {
    setLevel(Level.ERROR);
  }
  
  public void setApikey(String apikey) {
    m_apikey = apikey;
  }

  public void setEnv(String env) {
    m_env = env;
  }

  public void setUrl(final String url) {
    airbrakeNotifier.setUrl(url);
  }

  public AirbrakeNotice newNoticeFor(final Throwable throwable) {
    Backtrace backtrace = getBacktrace(throwable);

    return new AirbrakeNoticeBuilder(m_apikey,
        backtrace, throwable, m_env).newNotice();
  }

  private Backtrace getBacktrace(Throwable throwable) {
    return new Backtrace();
  }

  @Override
  protected void doPublish(ExtLogRecord record) {
    if (isLoggable(record) && record.getLevel().equals(Level.SEVERE)) {
      airbrakeNotifier.notify(newNoticeFor(throwable(record)));
    }
  }

  private Throwable throwable(ExtLogRecord record) {
	  if(record.getThrown() == null) {
		  return new Throwable(record.getFormattedMessage());
	  } else {
		  return record.getThrown();  
	  }
  }

  @Override
  public void flush() {
    // autoflush by airbrakeNotifier
  }

  @Override
  public void close() throws SecurityException {
    // nothing to close
  }
}
