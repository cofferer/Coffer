package com.example.lakmal.coffer.Model;


import com.example.lakmal.coffer.Adapter.SearchListItem;
import com.example.lakmal.coffer.R;

import java.util.ArrayList;
import java.util.List;

public class SearchData {

    //data
    private static final String[] title =
            {

                    "data 1",
                    "data 2",
                    "data 3",
                    "data 4",
                    "data 1",
                    "data 2",
                    "data 3",
                    "data 4"
            };

    private static final String[] off_percent =
            {

                    "10%",
                    "20%",
                    "30%",
                    "30%",
                    "10%",
                    "20%",
                    "30%",
                    "30%"
            };

    private static final String[] detail =
            {
                    "The Loebner Prize is an annual competition in artificial intelligence that awards prizes to the computer programs considered by the judges to be the most human-like. The format of the competition is that of a standard Turing test. In each round, a human judge simultaneously holds textual conversations with a computer program and a human being via computer. Based upon the responses, the judge must decide which is which.",
                    "The contest was launched in 1990 by Hugh Loebner in conjunction with the Cambridge Center for Behavioral Studies, Massachusetts, United States. It has since been associated with Flinders University, Dartmouth College, the Science Museum in London, University of Reading and Ulster University, Magee Campus, Derry, UK City of Culture. In 2004 and 2005, it was held in Loebner's apartment in New York City. Within the field of artificial intelligence, the Loebner Prize is somewhat controversial; the most prominent critic, Marvin Minsky, called it a publicity stunt that does not help the field along.",
                    "In addition, there are two one-time-only prizes that have never been awarded. $25,000 is offered for the first program that judges cannot distinguish from a real human and which can convince judges that the human is the computer program. $100,000 is the reward for the first program that judges cannot distinguish from a real human in a Turing test that includes deciphering and understanding text, visual, and auditory input. Once this is achieved, the annual competition will end.",
                    "In addition, there are two one-time-only prizes that have never been awarded. $25,000 is offered for the first program that judges cannot distinguish from a real human and which can convince judges that the human is the computer program. $100,000 is the reward for the first program that judges cannot distinguish from a real human in a Turing test that includes deciphering and understanding text, visual, and auditory input. Once this is achieved, the annual competition will end."
            };


    //data
    private static final String[] subtitle =
            {
                    "a",
                    "b",
                    "c",
                    "d",
                    "a",
                    "b",
                    "c",
                    "d"
            };
    //data
    private static final int[] images = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3

    };


    private static final int icon = android.R.drawable.ic_btn_speak_now;

    public static List<SearchListItem> getListData() {

        List<SearchListItem> data = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {

            SearchListItem item = new SearchListItem();


            item.setTitle(title[i]);
            item.setSubTitle(title[i]);
            // item.setDetail(detail[i]);

            data.add(item);

        }

        return data;

    }

}