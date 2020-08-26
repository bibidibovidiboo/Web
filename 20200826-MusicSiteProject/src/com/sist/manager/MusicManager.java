/*
 *  데이터 긁을 때 주의할 점 : CATENO 바꿔서 저장하면서 돌려야함 => 안그러면 1로만 저장됨
 *  바꿔야하는 부분 2가지 : 주소 & CATENO
 *  다시 긁을 때 mno도 1부터 하고싶으면 시퀀스도 지우고 다시 긁어야함 (DROP SEQUENCE)
 */
package com.sist.manager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import com.sist.dao.MusicDAO;
/*    
        문장1
        try
        {
                  문장2 ==> error ==> 종료
        }catch(Exception ex){}
        문장3
        문장4
        문장5
        
        try
        {
            for(int i=0;i<10;i++)
            {
               try
               {
                  == error
               }catch(Exception ex){ ==> i++}
            }
        }catch(Exception ex){종료}
 */
public class MusicManager {
    public void musicAllData()
    {
    	MusicDAO dao=new MusicDAO(); // 이거 꼭 있어야함
    	try
    	{
    		int k=1;
    		for(int i=1;i<=5;i++)
    		{
	    		Document doc=Jsoup.connect("https://www.genie.co.kr/genre/L0207?genreCode=L0207&pg="+i).get(); // 바뀌는 부분 1
	    		Elements title=doc.select("td.info a.title");
	    		Elements singer=doc.select("td.info a.artist");
	    		Elements album=doc.select("td.info a.albumtitle");
	    		Elements poster=doc.select("a.cover img");
	    		
	    		for(int j=0;j<title.size();j++)
	    		{
	    			try
	    			{
		    			MusicVO vo=new MusicVO();
		    			System.out.println("번호:"+ k++);
		    			System.out.println("cateno:1"); 
		    			System.out.println("제목:"+title.get(j).text());
		    			System.out.println("가수명:"+singer.get(j).text());
		    			System.out.println("앨범:"+album.get(j).text());
		    			System.out.println("포스터:"+poster.get(j).attr("src"));
	    			    System.out.println("==============================");
	    			    // vo에 값을 채운다 => DAO
	    			    vo.setCateno(10); // 바뀌는 부분2
	    			    vo.setTitle(title.get(j).text());
	    			    vo.setSinger(singer.get(j).text());
	    			    vo.setAlbum(album.get(j).text());
	    			    vo.setPoster(poster.get(j).attr("src"));
	    			    // DAO로 전송
	    			    dao.musicInsert(vo);
	    			    Thread.sleep(100); // 실무에서 X
	    			}catch(Exception ex){}
	    		}
	    		System.out.println("End...");
    		}
    		
    	}catch(Exception ex){}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        MusicManager m=new MusicManager();
        m.musicAllData();
	}
}