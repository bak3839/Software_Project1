package com.example.movie.process;

import com.example.movie.domain.MovieInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShowMovie {
    private final String key = "87f2d2380954a0c4c0569822a54ad548";

    public void getMovie(){
        String movieResult_response = "";

        String movieNm = "스파이더맨:";
        String curPage = "";
        String itemPerPage = "";
        String directorNm = "";
        String openStartDt = "";
        String openEndDt = "";
        String prdtStartYear = "";
        String prdtEndYear = "";
        String repNationCd = "";
        String[] movieTypeCdArr = new String[100];

        List<String> actorName = new ArrayList<>();
        try {
            KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);

            movieResult_response = service.getMovieList(true, curPage, itemPerPage,movieNm,directorNm,openStartDt,openEndDt,prdtStartYear, prdtEndYear,repNationCd,movieTypeCdArr);
            System.out.println(movieResult_response);

            JSONParser jsonParser = new JSONParser();

            Object obj = jsonParser.parse(movieResult_response);

            JSONObject jsonObject = (JSONObject) obj;

            // movieListResult JSON 가져오기
            JSONObject parse_MovieResult = (JSONObject) jsonObject.get("movieListResult");

            ObjectMapper objectMapper = new ObjectMapper();

            // movieList JSON 가져오기
            JSONArray parse_movieList = (JSONArray) parse_MovieResult.get("movieList");

            System.out.println("영화개수: " + parse_movieList.size());

            for(int i = 0; i < parse_movieList.size(); i++) {
                String movieCd = "";
                String prdtYear = "";
                String peopleNm = "";

                // String movieInfo_response = "";
                JSONObject movieList = (JSONObject) parse_movieList.get(i);

                // directors JSON 가져오기
                JSONArray parse_directors = (JSONArray) movieList.get("directors");

                for(int j = 0; j < parse_directors.size(); j++) {
                    JSONObject directors = (JSONObject) parse_directors.get(j);
                    peopleNm += (String) directors.get("peopleNm");
                    peopleNm += " ";
                }

                movieNm = (String) movieList.get("movieNm");
                movieCd = (String) movieList.get("movieCd");
                prdtYear = (String) movieList.get("prdtYear");

                /*movieInfo_response = service.getMovieInfo(true, movieCd);
                System.out.println(movieInfo_response);

                JSONParser jsonParser2 = new JSONParser();

                // 요청받은 영화 상세정보 Object 형식으로 JSON 으로 변형
                Object obj2 = jsonParser2.parse(movieInfo_response);

                JSONObject InfoObject = (JSONObject) obj2;

                // { } 로 묶인 JSON 은 JSONObject 형 사용
                // movieInfoResult JSON 가져오기
                JSONObject parse_movieInfoResult = (JSONObject) InfoObject.get("movieInfoResult");

                // movieInfo JSON 가져오기
                JSONObject parse_movieInfo = (JSONObject) parse_movieInfoResult.get("movieInfo");

                // [ ] 로 묶인 JSON 은 JSONArray 형 사용
                // actors JSON 가져오기
                JSONArray parse_actors = (JSONArray) parse_movieInfo.get("actors");

                // parse_actors(배우명) 크기 만큼 actorName 리스트에 넣어서 저장
                for(int j = 0; j < parse_actors.size(); j++) {
                    JSONObject actors = (JSONObject) parse_actors.get(j);
                    actorName.add((String) actors.get("peopleNm"));
                }*/

                System.out.println("제목:" + movieNm + "\n영화코드: " + movieCd + "\n개봉년도: " + prdtYear + "\n감독명: " + peopleNm + "\n\n");

                /*if(actorName.isEmpty()) continue;

                for(int k = 0; k < actorName.size(); k++){
                    System.out.print(actorName.get(k) + ", ");
                }
                System.out.println("\n");
                actorName.clear();*/
            }
        }catch (Exception e) {
            System.out.println("오류발생");
            e.printStackTrace();
        }
    }
}
