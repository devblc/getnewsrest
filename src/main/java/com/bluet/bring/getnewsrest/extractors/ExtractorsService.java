package com.bluet.bring.getnewsrest.extractors;

import java.io.IOException;
import java.util.Optional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;



@Service
public class ExtractorsService {
	
	private final String JUSTWATCH_URL = "https://ecstatic-keller-800717.netlify.app/cinema"; //"https://www.justwatch.com/br/novo";
	//private final String JUSTWATCH_URL = "https://www.justwatch.com/br/novo";
	
	public Optional<?> getProvidersInfo() { // List<MoviesProviders>
		try {
			return loadJsoup();
		} catch (IOException e) {
			return Optional.ofNullable(e.getMessage());
		}
	}
	
	private Optional<?> loadJsoup() throws IOException {
		Document doc = Jsoup.connect(JUSTWATCH_URL).get();
		Elements listUrl = doc.select("img[class='data-v-e2ed008c']");
		//Elements listUrl = doc.getElementsByClass("SideBarCard_sb_card_img__6STxt");
		Optional<String> result = Optional.ofNullable(listUrl.attr("src"));
		return result;
		

	}

}
