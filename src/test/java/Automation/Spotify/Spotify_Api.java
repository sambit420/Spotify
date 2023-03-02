package Automation.Spotify;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class Spotify_Api 
{
	String token ="Bearer BQBPuw0VshQzylK2uAd2nz2Hrup9ZDKgdI9LoIvHzXSqWnGO2aIL3CIAuZq8TA-z8t3XADsejqjSQ3W4LKV0jhAgrh4t0fn2bpuujLCw9E5RSOv6Lf0-v050USeFmkL9nEQv4FgiboRUkaoWGA9yZYO3O8nQaEaV1GH8nsa14aXyS46slVJoDSarVHMyx33IgkEght1KZN94maxQSJmVgc4I3l-GrZmKVVejqN9EQMm3bxPCvKFx1Oz16Bs86s-QVabFzwRHHh8";
	String user_id;
	@Test (priority=1)
	public void getCurrentUsersProfile() {
		Response res = given()
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/me");
		res.prettyPrint();
		res.then().statusCode(200);
		Assert.assertEquals(res.statusCode(), 200);
	}
	@Test(priority=2)
	public void userId() {
		Response res = given()
				.header("Content-Type", "application/json")
				.header("Authorization", token)
				.when()
				.get("https://api.spotify.com/v1/users/316k23c55ih6wmuj2wextqj3j5pm");
			res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
			String name = res.path("display_name");
			user_id =res.path("id");
			System.out.println(name);
	}
      @Test  (priority=3)   	
     public void createPlaylist() {
    	  Response res = given()
  				.header("Content-Type", "application/json")
  				.header("Authorization", token)
  				.body("{\"name\":\"Sambit Playlist\",\"description\":\"Sambit playlist description\",\"public\":false}")
  				.when()
  				.post("https://api.spotify.com/v1/users/"+ user_id +"/playlists");
    	  res.prettyPrint();
			res.then().statusCode(201);
			Assert.assertEquals(res.statusCode(), 201);
           }
      @Test (priority=4)
      public void getPlaylist() {
    	  Response res = given()
    				.header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/playlists/5uMLJYoRdRAmtZWqky7Roc?market=ES&fields=items(added_by.id%2Ctrack(name%2Chref%2Calbum(name%2Chref)))&additional_types=episode");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);      }
      @Test (priority=5)
      public void 	getUsersPlaylists() {
    	  Response res = given()
  				.header("Content-Type", "application/json")
  				.header("Authorization", token)
  				.when()
  				.get("https://api.spotify.com/v1/users/316k23c55ih6wmuj2wextqj3j5pm/playlists?limit=10&offset=5");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=6)
      public void 	getPlaylistItems() {
    	  Response res = given()
    				.header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/playlists/5uMLJYoRdRAmtZWqky7Roc/tracks?market=ES&fields=items(added_by.id%2Ctrack(name%2Chref%2Calbum(name%2Chref)))&limit=10&offset=5&additional_types=episode");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=7)
      public void getPlaylistCoverImage() {
    	  Response res = given()
  				.header("Content-Type", "application/json")
  				.header("Authorization", token)
  				.when()
  				.get("https://api.spotify.com/v1/playlists/5uMLJYoRdRAmtZWqky7Roc/images");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=8)
      public void getCurrentUsersPlaylists() {
    	  Response res = given()
    				.header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/me/playlists?limit=10&offset=3");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=9)
      public void updatePlaylistItems() {
    	  Response res = given()
  				.header("Content-Type", "application/json")
  				.header("Authorization", token)
  				.body("{\"range_start\":1,\"insert_before\":3,\"range_length\":2}")
  				.when()
    	         .put("https://api.spotify.com/v1/playlists/5uMLJYoRdRAmtZWqky7Roc/tracks?uris=spotify%3Atrack%3A4iV5W9uYEdYUVa79Axb7Rh%2Cspotify%3Atrack%3A1301WleyT98MSxVHPZCA6M");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
			}
      @Test (priority=10)
			public void removePlaylistItems() {
    	  Response res = given()
    	  .header("Content-Type", "application/json")
			.header("Authorization", token)
			.body("{\"tracks\":[{\"uri\":\"spotify:track:4iV5W9uYEdYUVa79Axb7Rh\"},{\"uri\":\"spotify:track:1301WleyT98MSxVHPZCA6M\"}]}")
			.when()
			  .delete("https://api.spotify.com/v1/playlists/5FZKA4iY8vQMeplneZoohI/tracks");
			  				 res.prettyPrint();
					res.then().statusCode(200);
					Assert.assertEquals(res.statusCode(), 200);
			}
      @Test (priority=11)
      public void getTracksAudioAnalysis() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl");
 		 res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
	}
      @Test (priority=12)
      public void getTracksAudioFeatures() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/audio-features");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
	}
      @Test (priority=13)
      public void getTrackAudioFeatures() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
	}
      @Test (priority=14)
      public void getSeveralTracks() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/tracks?market=ES&ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=15)
      public void getTracks() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get ("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl?market=ES");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=16)
      public void serchforItem() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.pathParam("q", "death%20race%20hindi")
    				.pathParam("type","track")
    				.pathParam("market", "ES")
    				.pathParam("limit", "10")
    				.pathParam("offset", "5")
    				.when()
    	  .get("https://api.spotify.com/v1/search?q={q}&type={type}&market={market}&limit={limit}&offset={offset}");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=17)
      public void getShows() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				//.pathParam("ids","5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ")
    				.when()
    			.get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ?market=ES");
    			
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=18)
      public void getShowsEpisodes() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/shows/38bS44xjbVVZ3No3ByF1dJ/episodes?market=ES&limit=10&offset=5");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
      }
      @Test (priority=19)
      public void getSeveralShows() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.pathParam("ids","5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ")
    				.when()
    	  .get("https://api.spotify.com/v1/shows?ids={ids}");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);
    }
      @Test (priority=20)
      public void 	getAvailableDevices() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/me/player/devices");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);			
      }
      @Test (priority=21)
      public void 	getCurrentlyPlayingTrack() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/me/player/currently-playing?market=ES&additional_types=episode");
    	  res.prettyPrint();
			res.then().statusCode(204);
			Assert.assertEquals(res.statusCode(), 204);	
      
      }
      @Test (priority=22)
      public void getAvailableMarkets() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/markets");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=23)
      public void getEpisodes() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/episodes/512ojhOuo1ktJprKbVcKyQ?market=ES");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
}
      @Test (priority=24)
      public void getSeveralChapter() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				//.pathParam("ids","77o6BIVlYM3msb4MMIL1jH%2C0Q86acNRm6V9GYx55SXKwf&market=ES")
    				.when()
                       .get("https://api.spotify.com/v1/chapters?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B&market=ES");
    	  res.prettyPrint();
    	  res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
              }
      @Test (priority=25)
      public void getAvailableGenreSeeds() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/recommendations/available-genre-seeds");
    	  res.prettyPrint();
    	  res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
            }
      @Test (priority=26)
      public void getSeveralbrowseCategories() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/browse/categories?country=SE&locale=sv_SE&limit=10&offset=5");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
      } 
      @Test (priority=27)
      public void getSingleBrowseCategory() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/browse/categories/dinner?country=SE&locale=sv_SE");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=28)
      public void getFeaturedPlaylistsPlayList() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/browse/categories/dinner/playlists?country=SE&limit=10&offset=5");
    	  res.prettyPrint();
			res.then().statusCode(200);
			Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=29)
    public void getNewReleases() {
  	  Response res = given()
  	    	  .header("Content-Type", "application/json")
  				.header("Authorization", token)
  				.when()
  				.get("https://api.spotify.com/v1/browse/new-releases?country=SE&limit=10&offset=5");
  	 res.prettyPrint();
		res.then().statusCode(200);
		Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=30)
      public void getRecommendations() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/recommendations?limit=10&market=ES&seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=31)
      public void getSeveralAudioBook() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/audiobooks?ids=7ouMYWpwJ422jRcDASZB7P%2C4VqPOruhp5EdPBeR92t6lQ%2C2takcwOaAZWiXQijPHIx7B&market=ES");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=32)
      public void getArtistsAlbums() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/albums");
    	  res.prettyPrint();
    		res.then().statusCode(200);
    		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=33)
      public void getArtistsRelatedArtists() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/related-artists");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=34)
      public void getArtistsTopTracks() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
                     .get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg/top-tracks?market=ES");
    	  res.prettyPrint();
    		res.then().statusCode(200);
    		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=35)
      public void getArtist() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=36)
      public void getSeveralArtists() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/artists/2CIMQHirSU0MQqyYHq0eOx");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=37)
      public void getAlbumTracks() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy/tracks?market=ES&limit=10&offset=5" );
    	  res.prettyPrint();
    		res.then().statusCode(200);
    		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=38)
      public void getAlbum() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy?market=ES");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=39)
      public void getSeveralAlbum() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/artists/4tZwfgrHOc3mvqYlEYSvVi");
    	  res.prettyPrint();
    		res.then().statusCode(200);
    		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test (priority=40)
      public void getFollowsArtists() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/me/following?type=artist&after=0I2XqVXqHScXjHhk6AYYRe&limit=10");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);	
    }
      @Test (priority=41)
      public void checkifUsersFollowPlaylist() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.when()
    				.get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers/contains?ids=jmperezperez%2Cthelinmichael%2Cwizzler");
    	  res.prettyPrint();
    		res.then().statusCode(200);
    		Assert.assertEquals(res.statusCode(), 200);	
      }
      @Test
      public void serch() {
    	  Response res = given()
    	    	  .header("Content-Type", "application/json")
    				.header("Authorization", token)
    				.pathParam("q", "death race hindi")
    				.pathParam("type","track")
    				.pathParam("market","ES")
    				.pathParam("limit","10")
    				.pathParam("offset", "5")
    				.when()
    				.get("https://api.spotify.com/v1/search?q={q}&type={type}&market={market}&limit={limit}&offset={offset}");
    	  res.prettyPrint();
  		res.then().statusCode(200);
  		Assert.assertEquals(res.statusCode(), 200);
      }
}