package com.dubhe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpTest
{

	@Test
	public void shouldTestRegExp() throws Exception
	{
		String regexp = "open_hr_insight.jsp|.js$";
		//regexp = ".js$";
		
		String charSequence = "/servlet/CheckSecurity/JSP/tctools/open_hr_insight.jsp";
		System.out.println(charSequence);
		
		Pattern pattern = Pattern.compile(regexp);
		
		// replaces pattern
		final Matcher fieldMatcher = pattern.matcher(charSequence);
		if (fieldMatcher.find() == true) {
			System.out.println("matches");
		}
		else {
			System.out.println("no matches");
		}
	}
	private void v1(String mergeField) {
		String simpleFieldPatternRegexp = "( *MERGEFIELD *)(\\$\\{)? *\"?(record\\.)?([a-zA-Z0-9_\\-\\.]*)\"? *(\\})?.*";
		Pattern simpleFieldPattern;

		System.out.println(mergeField);
		
		// we will use this if we have any '$' in the expression, meaning we're using
		// the full freemarker syntax
		String fieldPatternRegexp = "\\$\\{ *?(record\\.)?([a-zA-Z0-9_\\-\\.]*)\"? *([\\?\\}])";
		Pattern fieldPattern;

		simpleFieldPattern = Pattern.compile(simpleFieldPatternRegexp);
		fieldPattern = Pattern.compile(fieldPatternRegexp);
		
		// checks old sytax
		if (mergeField.contains("$")) {
			// we're using the full freemarker syntax
			final Matcher fieldMatcher = fieldPattern.matcher(mergeField);
			System.out.println( fieldMatcher.replaceAll("\\${" + "record." + "$2$3"));
		}
		else {
			// we are using the basic syntax
			final Matcher fieldMatcher = simpleFieldPattern.matcher(mergeField);
			System.out.println( fieldMatcher.replaceAll("$1 \\${" + "record." + "$4}"));
		}
	}
	
	private void v2(String mergeField) {
		
		String fieldPatternRegexp = " *(MERGEFIELD) *(\\$ *\\{)? *\\\"?(record\\.)?([a-zA-Z0-9_]*)\\\"? *(\\})?( *\\\\\\* *MERGEFORMAT *)?";
		
		Pattern fieldPattern;
		
		System.out.println(mergeField);
		
		fieldPattern = Pattern.compile(fieldPatternRegexp);
		
		// replaces pattern
		final Matcher fieldMatcher = fieldPattern.matcher(mergeField);
		if (fieldMatcher.matches()) {
			System.out.println(fieldMatcher.replaceAll("$1 \\${" + "record." + "$4} $6"));
		}
		else {
			System.out.println( mergeField);
		}
	}
	
	private void v3(String mergeField) {
		
		String fieldPatternRegexp = " *(MERGEFIELD) *\\\"?(\\[\\\"?[a-zA-Z0-9_\\# =\\\"]*\\])?( *\\$ *\\{)? *\\\"?(record\\.)?([a-zA-Z0-9_]*)\\\"? *(\\})?( *\\\\\\* *MERGEFORMAT *)?";
		String bb = " *(MERGEFIELD) *(\\\")?(\\[[\\/a-zA-Z0-9_\\# =\\\"]*\\])?( *\\$ *\\{)? *\\\"?(record\\.)?([a-zA-Z0-9_]*)([\\?\\(\\/\\)a-zA-Z0-9_\\# =\\\"]*)? *(\\})?(\\\")?( *\\\\\\* *MERGEFORMAT *)?";
		String aa = " *(MERGEFIELD) *(\\\")?(\\[[\\/a-zA-Z0-9_\\# =\\\"]*\\])?( *\\$ *\\{)? *\\\"?(record\\.)?([a-zA-Z0-9_]*)([\\?\\(\\/\\)a-zA-Z0-9_\\# =\\\"]*)? *(\\})?(\\\")?( *\\\\\\* *MERGEFORMAT *)?";
		String cc = " *(MERGEFIELD) *(\\\")?(\\[[\\\\\\/a-zA-Z0-9_\\# =\\\"]*\\])?( *\\$ *\\{)? *\\\"?(record\\.)?([a-zA-Z0-9_]*)([\\\\\\?\\(\\/\\)a-zA-Z0-9_\\# =\\\"]*)? *(\\})?(\\\")?( *\\\\\\* *MERGEFORMAT *)?";
		String dd = " *(MERGEFIELD) *(\\\")?(\\[.*\\])?( *\\$ *\\{)? *\\\"?(record\\.)?([a-zA-Z0-9_]*)([\\\\\\?\\(\\/\\)a-zA-Z0-9_\\# =\\\"]*)? *(\\})?(\\\")?( *\\\\\\* *MERGEFORMAT *)?";
		Pattern fieldPattern;
		
		System.out.println(mergeField);
		
		fieldPattern = Pattern.compile(fieldPatternRegexp);
		
		// replaces pattern
		final Matcher fieldMatcher = fieldPattern.matcher(mergeField);
		System.out.println(fieldMatcher.replaceAll("$1 $2 $3" + "record." +" $5 $6"));
		if (fieldMatcher.matches()) {
			System.out.println(fieldMatcher.replaceAll("$1 \\${" + "record." + "$4} $6"));
		}
		else {
			System.out.println("no match " + mergeField);
		}
	}
	
	@Test
	public void shouldTestMergeFieldRegexpV1V2() throws Exception
	{
		System.out.println( "V1");
		String mergeField = "MERGEFIELD [#if record.STD_ID_GENDER == \"1\"]";
		v1(mergeField);
		
		mergeField = "MERGEFIELD [#if STD_ID_GENDER == \"1\"]";
		v1(mergeField);
		
		mergeField = "MERGEFIELD SALFICHE_COMMUNE";
		v1(mergeField);
		
		mergeField = "MERGEFIELD  ${record.SALFICHE_COMMUNE}";
		v1(mergeField);
		mergeField = "MERGEFIELD \"[setting locale=\"fr_FR\"] ${N1_STD_DT_BIRTH?date(\"dd/MM/yyyy\")?string(\"dd MMMM yyyy\")}\" \\* MERGEFORMAT";
		v1(mergeField);
		
		mergeField = "MERGEFIELD \"[setting locale=\"fr_FR\"] ${record.N1_STD_DT_BIRTH?date(\"dd/MM/yyyy\")?string(\"dd MMMM yyyy\")}\" \\* MERGEFORMAT";
		v1(mergeField);
		
		
		
		System.out.println( "V2");
		mergeField = "MERGEFIELD [#if record.STD_ID_GENDER == \"1\"]";
		v2(mergeField);
		
		mergeField = "MERGEFIELD [#if STD_ID_GENDER == \"1\"]";
		v2(mergeField);
		
		mergeField = "MERGEFIELD SALFICHE_COMMUNE";
		v2(mergeField);
		
		mergeField = "MERGEFIELD  ${record.SALFICHE_COMMUNE}";
		v2(mergeField);
		mergeField = "MERGEFIELD \"[setting locale=\"fr_FR\"] ${N1_STD_DT_BIRTH?date(\"dd/MM/yyyy\")?string(\"dd MMMM yyyy\")}\" \\* MERGEFORMAT";
		v2(mergeField);
		
		mergeField = "MERGEFIELD \"[setting locale=\"fr_FR\"] ${record.N1_STD_DT_BIRTH?date(\"dd/MM/yyyy\")?string(\"dd MMMM yyyy\")}\" \\* MERGEFORMAT";
		v2(mergeField);
		
		System.out.println( "V3");
		mergeField = "MERGEFIELD [#if record.STD_ID_GENDER == \"1\"]";
		v3(mergeField);
		
		mergeField = "MERGEFIELD [#if STD_ID_GENDER == \"1\"]";
		v3(mergeField);
		
		mergeField = "MERGEFIELD SALFICHE_COMMUNE";
		v3(mergeField);
		
		mergeField = "MERGEFIELD  ${record.SALFICHE_COMMUNE}";
		v3(mergeField);
		mergeField = "MERGEFIELD \"[setting locale=\"fr_FR\"] ${N1_STD_DT_BIRTH?date(\"dd/MM/yyyy\")?string(\"dd MMMM yyyy\")}\" \\* MERGEFORMAT";
		v3(mergeField);
		
		mergeField = "MERGEFIELD \"[setting locale=\"fr_FR\"] ${record.N1_STD_DT_BIRTH?date(\"dd/MM/yyyy\")?string(\"dd MMMM yyyy\")}\" \\* MERGEFORMAT";
		v3(mergeField);
		

	}

}
