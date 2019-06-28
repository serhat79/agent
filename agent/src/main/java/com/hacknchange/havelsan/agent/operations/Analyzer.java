package com.hacknchange.havelsan.agent.operations;

import com.hacknchange.havelsan.agent.models.Topic;
import com.hacknchange.havelsan.agent.services.IReadService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Analyzer {

    @Autowired
    private IReadService topicService;

    public Analyzer() {
    }

    public static int calculateDistance(CharSequence source, CharSequence target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("Parameter must not be null");
        }
        int sourceLength = source.length();
        int targetLength = target.length();
        if (sourceLength == 0) return targetLength;
        if (targetLength == 0) return sourceLength;
        int[][] dist = new int[sourceLength + 1][targetLength + 1];
        for (int i = 0; i < sourceLength + 1; i++) {
            dist[i][0] = i;
        }
        for (int j = 0; j < targetLength + 1; j++) {
            dist[0][j] = j;
        }
        for (int i = 1; i < sourceLength + 1; i++) {
            for (int j = 1; j < targetLength + 1; j++) {
                int cost = source.charAt(i - 1) == target.charAt(j - 1) ? 0 : 1;
                dist[i][j] = Math.min(Math.min(dist[i - 1][j] + 1, dist[i][j - 1] + 1), dist[i - 1][j - 1] + cost);
                if (i > 1 &&
                        j > 1 &&
                        source.charAt(i - 1) == target.charAt(j - 2) &&
                        source.charAt(i - 2) == target.charAt(j - 1)) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 2][j - 2] + cost);
                }
            }
        }
        return dist[sourceLength][targetLength];
    }

    public  List<Topic> getResults(String question)  throws Exception {

        List<Topic> topics = new ArrayList<>();
        List<Topic> result = new ArrayList<>();
        topics = null;



//        for (int i = 0; i < topics.size(); i++) {
//            int sum = 0;
//            for (int j = 0; j < topics.get(i).getTags().size(); j++) {
//                String tag = topics.get(i).getTags().get(j);
//                sum += calcSimilarity(question, tag);
//            }
//
//
//            topics.get(i).setOrder(sum);
//             System.out.println("sum:" + sum);
//            // result.add(topics.get(i).getDetail());
//        }
        Collections.sort(topics);
//
//        for (int i = 0; i < topics.size(); i++) {
//            if(topics.get(i).getOrder()!=0)
//                result.add(topics.get(i));
//        }

        for (int i = 0; i < topics.size(); i++) {
            // System.out.println(topics.get(i).getOrder());
        }

        return result;
    }

    public static ArrayList<String> ngramsCalculate(ArrayList<String> words, int n) {
        if (n <= 1) {
            return new ArrayList<String>(words);
        }

        ArrayList<String> ngrams = new ArrayList<String>();

        int c = words.size();
        for (int i = 0; i < c; i++) {
            if ((i + n - 1) < c) {
                int stop = i + n;
                String ngramWords = words.get(i);

                for (int j = i + 1; j < stop; j++) {
                    ngramWords += " " + words.get(j);
                }

                ngrams.add(ngramWords);
            }
        }

        return ngrams;
    }


    public static ArrayList<String> partition(String soru) {


        ArrayList<ArrayList<String>> kelime = new ArrayList<ArrayList<String>>();
        ArrayList<String> cek = new ArrayList<>();


        ArrayList<String> listWords = new ArrayList<>();
        String[] arrWord = soru.split(" ");
        for (int i = 0; i < arrWord.length; i++) {
            listWords.add(arrWord[i]);
        }

        int n = listWords.size();
        ArrayList<Integer> sizee = new ArrayList<>();

        for (int i = 0; i < n; i++)
            sizee.add(i + 1);

        for (int i = 0; i < n; i++) {
            ArrayList<String> analiz = ngramsCalculate(listWords, sizee.get(i));
            int c = analiz.size();
            int last = c - 1;

            for (int j = 0; j <= last; j++) {
                if (!cek.contains(analiz.get(j)))
                    cek.add(analiz.get(j));
            }
        }
        return cek;
    }


    public static int calcSimilarity(String soru, String cevap) {

        ArrayList<String> cek = partition(soru);
        ArrayList<String> cekCevap = partition(cevap);

        int a = 0, sim = 0;
        for (int i = 0; i < cek.size(); i++) {

            for (int j = 0; j < cekCevap.size(); j++) {
                a = calculateDistance(cek.get(i), cekCevap.get(j));
                if (a < 2)
                    sim = sim + a + 1;
            }
        }

        return sim;
    }
}
