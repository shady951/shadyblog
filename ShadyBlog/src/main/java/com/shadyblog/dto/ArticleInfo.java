package com.shadyblog.dto;

import java.util.List;

import com.shadyblog.pojo.Article;
import com.shadyblog.pojo.Keyword;

public class ArticleInfo {

	public Article article;
	
	public List<Keyword> keywordList;
	
	public String dateString;

	public ArticleInfo(Article article, List<Keyword> keywordList, String dateString) {
		this.article = article;
		this.keywordList = keywordList;
		this.dateString = dateString;
	}

	public Article getArticle() {
		return article;
	}

	public List<Keyword> getKeywordList() {
		return keywordList;
	}

	public String getDateString() {
		return dateString;
	}

	@Override
	public String toString() {
		return "ArticleInfo [Article=" + article + ", keywordList=" + keywordList + ", date=" + dateString + "]";
	}
	
}
