package programmers.kakaoBlindRecruitment2019;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Level3_MatchingScore {
	// 아래 규칙으로 검색어에 대한 웹페이지의 매칭점수 계산
	// 	* 한 웹페이지에 대해서 기본 점수, 외부 링크, 링크 점수, 매칭 점수를 구할 수 있음
	//	* 한 웹페이지의 기본점수 -> 해당 웹페이지의 텍스트 중, 검색어가 등장하는 횟수(대소문자 무시)
	// 	* 한 웹페이지의 외부 링크 수 -> 해당 웹페이지에서 다른 외부 페이지로 연결된 링크의 개수
	//	* 한 웹페이지의 링크 점수 -> 해당 웹페이지로 링크가 걸린 다음 웹페이지의 기본 점수 / 외부 링크 수의 총합
	//	* 한 웹페이지의 매칭 점수 -> 기본점수와 링크점수의 합산
	// 검색어 word, 웹페이지의 HTML 목록 pages, 매칭 점수가 가장 높은 웹페이지의 index 구하기
	
	// 1 <= pages 길이 <= 20
	// 1 <= 한 웹페이지의 문자열 길이 <= 1500
	// 웹페이지 index -> pages배열의 index와 같음
	// 한 웹페이지의 url -> HTML 태그 내에 태그의 값으로 주어짐
	// 한 웹페이지의 모든 외부 링크 -> <a href="">
	// 모든 url -> https://로 시작
	// word -> 하나의 영어 단어, 알파벳 소문자와 대문자로만 이루어짐
	// 1 <= word 길이 <= 12
	// 대소문자 구분 무시하고 찾음
	// 검색어는 단어 단위로 비교, 단어와 완전히 일치하는 경우에만 반영
	//	단어 -> 알파벳을 제외한 다른 모든 문자로 구분
	// 동일한 매칭점수 가진 웹페이지 여러 개 -> index 번호가 가장 작은 것을 return
	
	static HashMap<String, Link> links;
	public static int solution(String word, String[] pages) {
		links = new HashMap<>();
		Pattern webPageUrlPattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
		Pattern externalUrlPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
		Pattern wordPattern = Pattern.compile("\\b(?i)" + word + "\\b");
		Matcher webPageUrlMatcher, externalUrlMatcher, wordMatcher;
		
		ArrayList<String> externalUrl;
		Link newLink;
		String body;
		String webPageUrl = "";
		for(int idx = 0; idx < pages.length; idx++) {
			webPageUrlMatcher = webPageUrlPattern.matcher(pages[idx]);
			externalUrlMatcher = externalUrlPattern.matcher(pages[idx]);
			if(webPageUrlMatcher.find())
				webPageUrl = webPageUrlMatcher.group().split("=")[2].replaceAll("\"", "");
			
			newLink = new Link(idx, webPageUrl);
			
			externalUrl = new ArrayList<>();
			
			while(externalUrlMatcher.find())
				externalUrl.add(externalUrlMatcher.group().split("\"")[1]);
			newLink.setExternalUrl(externalUrl);
			
			body = pages[idx].split("<body>")[1].split("</body>")[0].replaceAll("[0-9]", " ");
			wordMatcher = wordPattern.matcher(body);
			int count = 0;
			while(wordMatcher.find()) count++;
			newLink.point = count;
			newLink.setLinkPoint();
			links.put(newLink.url, newLink);
		}
		
		for(Link link : links.values()) {
			for(String external : link.externalUrl) {
				if(links.containsKey(external))
					links.get(external).point += link.link_point;
			}
		}
		
		double max_point = 0;
		int answer = 0;
		for(Link link : links.values()) {
			if(link.point == max_point) {
				if(link.index < answer) answer = link.index;
			} else if(link.point > max_point) {
				max_point = link.point;
				answer = link.index;
			}
		}
		return answer;
	}
	
	static class Link {
		String url;
		String[] externalUrl;
		double point, link_point;
		int index;
		
		public Link(int i, String url) {
			this.index = i;
			this.url = url;
		}
		
		public void setExternalUrl(ArrayList<String> urls) {
			this.externalUrl = new String[urls.size()];
			for(int idx = 0; idx < urls.size(); idx++)
				externalUrl[idx] = urls.get(idx);
		}
		
		public void setLinkPoint() {
			this.link_point = this.point / externalUrl.length;
		}
	}
	
	public static void main(String[] args) {
		String word = "blind";
		String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
		System.out.println(solution(word, pages));
	}
}
