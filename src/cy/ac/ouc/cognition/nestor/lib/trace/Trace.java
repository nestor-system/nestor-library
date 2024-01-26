package cy.ac.ouc.cognition.nestor.lib.trace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cy.ac.ouc.cognition.nestor.lib.base.NESTORBase;

public class Trace extends NESTORBase {
	

	static enum StreamType {
		OUT, ERR;
	}
	

	public static enum TraceLevel {
		CRITICAL, IMPORTANT, NORMAL, LOW, INFO;
	}
	
	

	public static void println(StreamType stream, String textToPrint, boolean printTimestamp) {
		
        LocalDateTime now = LocalDateTime.now();
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getSysParameters().getSystem_TraceTimestampFormat());
        String nowString = (printTimestamp && getSysParameters().isSystem_TraceUseTimestamp()) ?
        						now.format(dtf) + " " :
        						"";

		if (stream == StreamType.ERR)
			System.err.println(nowString + textToPrint);
		else
			System.out.println(nowString + textToPrint);
	}

	

	public static void outln(TraceLevel traceLevel, String textToPrint, boolean printTimestamp) {
		if (traceLevel.ordinal() <= getSysParameters().getSystem_TraceLevel())
		Trace.println(StreamType.OUT, textToPrint, printTimestamp);
	}

	public static void outln(TraceLevel traceLevel, String textToPrint) {
		Trace.outln(traceLevel, textToPrint, true);
	}

	public static void outln(String textToPrint, boolean printTimestamp) {
		Trace.outln(TraceLevel.valueOf(getSysParameters().getSystem_DefaultTraceLevel()), textToPrint, printTimestamp);
	}

	public static void outln(String textToPrint) {
		Trace.outln(TraceLevel.valueOf(getSysParameters().getSystem_DefaultTraceLevel()), textToPrint, true);
	}


	
	public static void errln(String textToPrint, boolean printTimestamp) {
		Trace.println(StreamType.ERR, textToPrint, printTimestamp);
	}

	public static void errln(String textToPrint) {
		Trace.errln(textToPrint, true);
	}

}
