package com.diegoram;

/**
 * Hello world!
 *
 */
import java.util.ArrayList;
import java.util.List;

import twitter4j.*;
public final class TwitterStreamSample extends StatusAdapter 
{
    
        public static void main(String[] args) throws TwitterException {
        	
        	FilterQuery query = new FilterQuery();
        	
        	// wfrank 7897072
        	//ponja 38299186
        	
        	String[] tracks = {"diegoram","cfkargentina"};
        	//query.track(tracks);
        	
        	long[] ids = {7897072L,38299186L,17760769L};
        
        	query.follow(ids);
        	
            TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
            StatusListener listener = new StatusListener() {
                @Override
                public void onStatus(Status status) {
                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                }

                @Override
                public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                    System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
                }

                @Override
                public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                    System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
                }

                @Override
                public void onScrubGeo(long userId, long upToStatusId) {
                    System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
                }

                //@Override
                //public void onStallWarning(StallWarning warning) {
                //    System.out.println("Got stall warning:" + warning);
                //}

                @Override
                public void onException(Exception ex) {
                    ex.printStackTrace();
                }
            };
            twitterStream.addListener(listener);
            twitterStream.filter(query);
        }
    
}
