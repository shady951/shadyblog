package com.shadyblog.util;

import java.util.List;

import org.shady4j.framework.util.CollectionUtil;

import com.shadyblog.pojo.Keyword;

public final class KeywordUtil {

	public static String keywordListTokeywords(List<Keyword> keywordList) {
		StringBuilder stringBuilder = new StringBuilder();
		if(CollectionUtil.isNotEmpty(keywordList)) {
			for(Keyword keyword : keywordList) {
				stringBuilder.append(keyword.getName()).append(",");
			}
			return stringBuilder.substring(0, stringBuilder.length() - 1);
		}
		return null;
	}
}
