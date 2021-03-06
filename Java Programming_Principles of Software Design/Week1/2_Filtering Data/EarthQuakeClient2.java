import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
   public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
   }

   public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
       ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       for(QuakeEntry qe : quakeData) { 
           if (f.satisfies(qe)) { 
               answer.add(qe); 
           } 
       } 
        
       return answer;
   } 

   public void quakesWithFilter() { 
       EarthQuakeParser parser = new EarthQuakeParser(); 
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       String source = "data/nov20quakedatasmall.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       /*
       Filter f1 = new MagnitudeFilter(4.0, 5.0);
       Filter f2 = new DepthFilter(-35000.0, -12000.0);
       ArrayList<QuakeEntry> result_mid = filter(list, f1);
       ArrayList<QuakeEntry> result_final = filter(result_mid, f2);
       for(QuakeEntry qe: result_final){
           System.out.println(qe);
       }
       */
       Location loc = new Location(35.42, 139.43);
       Filter f3 = new DistanceFilter(loc,10000000); 
       Filter f4 = new PhraseFilter("end", "Japan");
       ArrayList<QuakeEntry> result_mid = filter(list, f3);
       ArrayList<QuakeEntry> result_final = filter(result_mid, f4);
       for(QuakeEntry qe: result_final){
           System.out.println(qe);
       }
   }
   
   public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
   }

   public void dumpCSV(ArrayList<QuakeEntry> list) {
       System.out.println("Latitude,Longitude,Magnitude,Info");
       for(QuakeEntry qe : list){
           System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
               qe.getLocation().getLatitude(),
               qe.getLocation().getLongitude(),
               qe.getMagnitude(),
               qe.getInfo());
       }
   }
   
   public void testMatchAllFilter2(){
       EarthQuakeParser parser = new EarthQuakeParser(); 
       //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       
       MatchAllFilter maf = new MatchAllFilter();
       Filter f1 = new MagnitudeFilter(0.0, 5.0);
       Location loc = new Location(55.7308, 9.1153);
       Filter f2 = new DistanceFilter(loc, 3000000 );
       Filter f3 = new PhraseFilter("any", "e");
       //Filter f4 = new DepthFilter(-180000.0, -30000.0 );
       
       maf.addFilter(f1);
       maf.addFilter(f2);
       maf.addFilter(f3);
       //maf.addFilter(f4);
       
       ArrayList<QuakeEntry> result = filter(list, maf);
       for(QuakeEntry qe: result){
           System.out.println(qe);
       }
       System.out.println("number of earthquake found is: " + result.size());
   }
}    

