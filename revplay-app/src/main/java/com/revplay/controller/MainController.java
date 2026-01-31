package com.revplay.controller;

import java.util.Scanner;

import com.revplay.exception.RevPlayException;
import com.revplay.model.Album;
import com.revplay.model.Artist;
import com.revplay.model.Song;
import com.revplay.model.User;
import com.revplay.service.ArtistService;
import com.revplay.service.AuthService;
import com.revplay.service.FavoriteService;
import com.revplay.service.GuestService;
import com.revplay.service.MusicService;
import com.revplay.service.PlaylistService;

public class MainController 
{
	private AuthService authService = new AuthService();
    private GuestService guestService = new GuestService();
    private MusicService musicService = new MusicService();
    private PlaylistService playlistService = new PlaylistService();
    private ArtistService artistService = new ArtistService();
    private FavoriteService favoriteService = new FavoriteService();

    public void start() 
    {
    	Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (true) 
        {
        	System.out.println("=================================");
        	System.out.println("---   Welcome to RevPlay   ---");
        	System.out.println("=================================");
        	System.out.println("1. Guest");
        	System.out.println("2. User");
        	System.out.println("3. Artist");
        	System.out.println("4. Exit");
        	System.out.println("=================================");
        	System.out.print("Enter your choice: ");

            try 
            {
                choice = sc.nextInt();
            } 
            catch (Exception e) 
            {
                System.out.println(" Invalid input. Please enter a number.");
                sc.nextLine(); 
                continue;
            }

            switch (choice) 
            {
                case 1:
                    System.out.println(" Guest mode selected");
                    guestMenu(sc);
                    break;

                case 2:
                    System.out.println(" User mode selected");
                    userMenu(sc);
                    break;

                case 3:
                    System.out.println(" Artist mode selected");
                    artistMenu(sc);
                    break;

                case 4:
                    System.out.println("Thank you for using RevPlay ");
                    sc.close();
                    return;

                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }
    }
    private void guestMenu(Scanner sc) 
    {

        int choice = 0;

        while (true) 
        {
            System.out.println("--------- Guest Menu ---------");
            System.out.println("1. View Song");
            System.out.println("2. View Album");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try 
            {
                choice = sc.nextInt();
            } 
            catch (Exception e) 
            {
                System.out.println(" Invalid input. Enter a number.");
                sc.nextLine(); 
                continue;
            }

            switch (choice) 
            {

                case 1:
                    System.out.print("Enter Song ID: ");
                    int songId = sc.nextInt();
                    try 
                    {
                        System.out.println(guestService.viewSong(songId));
                    } 
                    catch (Exception e) 
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter Album ID: ");
                    int albumId = sc.nextInt();
                    try 
                    {
                        System.out.println(guestService.viewAlbum(albumId));
                    } 
                    catch (Exception e) 
                    {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    return; 

                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
    private void userMenu(Scanner sc) {
    	
    	boolean isLoggedIn = false;
    	User loggedInUser = null;

        int choice = 0;

        while (true) {
        	System.out.println("--------- User Menu ---------");
        	System.out.println("1. Register");
        	System.out.println("2. Login");
        	System.out.println("3. Search Library");
        	System.out.println("4. Browse by Category");
        	System.out.println("5. Play Song");
        	System.out.println("6. Pause Song");
        	System.out.println("7. Add Song to Favorites");
        	System.out.println("8. View Favorite Songs");
        	System.out.println("9. Create Playlist");
        	System.out.println("10. Add Song to Playlist");
        	System.out.println("11. Remove Song from Playlist");
        	System.out.println("12. View My Playlists");
        	System.out.println("13. Update Playlist");
        	System.out.println("14. Delete Playlist");
        	System.out.println("15. View Public Playlists");
        	System.out.println("16. View Play History");
        	System.out.println("17. Change Password");
        	System.out.println("18. Forgot Password");
        	System.out.println("19. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println(" Invalid input. Enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) 
            {
            
            case 1:
                sc.nextLine();

                User user = new User();

                System.out.print("Enter User ID: ");
                user.setUserId(sc.nextInt());
                sc.nextLine();

                System.out.print("Enter Name: ");
                user.setName(sc.nextLine());

                System.out.print("Enter Email: ");
                user.setEmail(sc.nextLine());

                System.out.print("Enter Password: ");
                user.setPassword(sc.nextLine());

                try {
                    authService.registerUser(user);
                    System.out.println("User registered successfully");
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 2:
                sc.nextLine(); 

                System.out.print("Enter Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                try 
                {
                    loggedInUser = authService.loginUser(email, password);
                    isLoggedIn = true;
                    System.out.println(" Login successful. Welcome " + loggedInUser.getName());
                } 
                catch (Exception e) 
                {
                    System.out.println(" " + e.getMessage());
                }
                break;

            	case 3:
                sc.nextLine();
                System.out.print("Enter keyword to search: ");
                String keyword = sc.nextLine();

                musicService.searchLibrary(keyword);
                break;
                
            	case 4:
            	    System.out.println("Browse by:");
            	    System.out.println("1. Genre");
            	    System.out.println("2. Artist");
            	    System.out.println("3. Album");
            	    System.out.print("Enter choice: ");
            	    int browseChoice = sc.nextInt();
            	    sc.nextLine();

            	    switch (browseChoice) {
            	        case 1:
            	            System.out.print("Enter Genre: ");
            	            musicService.browseByGenre(sc.nextLine());
            	            break;

            	        case 2:
            	            System.out.print("Enter Artist Name: ");
            	            musicService.browseByArtist(sc.nextLine());
            	            break;

            	        case 3:
            	            System.out.print("Enter Album Name: ");
            	            musicService.browseByAlbum(sc.nextLine());
            	            break;

            	        default:
            	            System.out.println("Invalid browse option");
            	    }
            	    break;


            	case 5:
                if (!isLoggedIn) 
                {
                    System.out.println(" Please login first.");
                    break;
                }

                System.out.print("Enter Song ID: ");
                int songId = sc.nextInt();

                musicService.playSong(songId, loggedInUser.getUserId());
                break;

                case 6:
                    musicService.pauseSong();
                    break;

                case 7:
                    if (!isLoggedIn) {
                        System.out.println("Please login first.");
                        break;
                    }
                    System.out.print("Enter Song ID: ");
                    int favSongId = sc.nextInt();
                    favoriteService.addFavorite(loggedInUser.getUserId(), favSongId);
                    break;
                    
                case 8:
                    if (!isLoggedIn) {
                        System.out.println("Please login first.");
                        break;
                    }
                    favoriteService.viewFavorites(loggedInUser.getUserId());
                    break;

                case 9:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    System.out.print("Enter Playlist ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Playlist Name: ");
                    String pname = sc.nextLine();

                    System.out.print("Public? (1-Yes, 0-No): ");
                    int pub = sc.nextInt();

                    playlistService.createPlaylist(loggedInUser.getUserId(), pid, pname, pub);
                    break;

                case 10:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    System.out.print("Enter Playlist ID: ");
                    int pId = sc.nextInt();
                    System.out.print("Enter Song ID: ");
                    int sId = sc.nextInt();

                    playlistService.addSongToPlaylist(pId, sId);
                    break;

                case 11:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    System.out.print("Enter Playlist ID: ");
                    int rpId = sc.nextInt();
                    System.out.print("Enter Song ID: ");
                    int rsId = sc.nextInt();

                    playlistService.removeSongFromPlaylist(rpId, rsId);
                    break;

                case 12:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    playlistService.viewUserPlaylists(loggedInUser.getUserId());
                    break;

                case 13:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    System.out.print("Enter Playlist ID: ");
                    int upId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    String newName = sc.nextLine();

                    playlistService.updatePlaylist(upId, newName);
                    break;

                case 14:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    System.out.print("Enter Playlist ID: ");
                    playlistService.deletePlaylist(sc.nextInt());
                    break;

                case 15:
                	 if (!isLoggedIn) {
                	        System.out.println("Please login first.");
                	        break;
                	    }
                    playlistService.viewPublicPlaylists();
                    break;


                case 16:
                    if (!isLoggedIn) {
                        System.out.println(" Please login first.");
                        break;
                    }
                    musicService.viewHistory(loggedInUser.getUserId());
                    break;
                    
                case 17:
                    if (!isLoggedIn) {
                        System.out.println("Please login first.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new password: ");
                    String newPass = sc.nextLine();

                    try {
                        authService.changePassword(loggedInUser.getUserId(), newPass);
                        System.out.println("Password changed successfully");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 18:
                    sc.nextLine();
                    System.out.print("Enter registered email: ");
                    email = sc.nextLine();
                    System.out.print("Enter new password: ");
                    String resetPass = sc.nextLine();

                    try {
                        authService.forgotPassword(email, resetPass);
                        System.out.println("Password reset successful");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;


                case 19:
                    return;

                default:
                    System.out.println(" Invalid choice.");
            }
        }
    }
    private void artistMenu(Scanner sc) 
    {
    	boolean isLoggedIn = false;
    	Artist loggedInArtist = null;
        int choice = 0;

        while (true) 
        {
            System.out.println("--------- Artist Menu ---------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Upload Song");
            System.out.println("4. Create Album");
            System.out.println("5. Update Song");
            System.out.println("6. Update Album");
            System.out.println("7. Delete Song");
            System.out.println("8. Delete Album");
            System.out.println("9. View Song Play Count");
            System.out.println("10. View Users Who Favorited My Song");
            System.out.println("11. Back to Main Menu");
            System.out.print("Enter your choice: ");

            try 
            {
                choice = sc.nextInt();
            } 
            catch (Exception e) 
            {
                System.out.println(" Invalid input. Enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) 
            {
            
            case 1:
                sc.nextLine();

                Artist artist = new Artist();

                System.out.print("Enter Artist ID: ");
                artist.setArtistId(sc.nextInt());
                sc.nextLine();

                System.out.print("Enter Name: ");
                artist.setName(sc.nextLine());

                System.out.print("Enter Email: ");
                artist.setEmail(sc.nextLine());

                System.out.print("Enter Password: ");
                artist.setPassword(sc.nextLine());

                System.out.print("Enter Genre: ");
                artist.setGenre(sc.nextLine());

                try {
                    authService.registerArtist(artist);
                    System.out.println("Artist registered successfully");
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 2:
                sc.nextLine();

                System.out.print("Enter Artist Email: ");
                String email = sc.nextLine();

                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                try 
                {
                    loggedInArtist = authService.loginArtist(email, password);
                    isLoggedIn = true;
                    System.out.println(" Artist login successful. Welcome " + loggedInArtist.getName());
                } 
                catch (Exception e) 
                {
                    System.out.println(" Login failed: " + e.getMessage());
                }
                break;


            case 3:
                if (!isLoggedIn) {
                    System.out.println("Please login first.");
                    break;
                }

                sc.nextLine();
                System.out.print("Song Title: ");
                String title = sc.nextLine();

                System.out.print("Genre: ");
                String genre = sc.nextLine();

                System.out.print("Duration: ");
                int duration = sc.nextInt();

                Song song = new Song();
                song.setTitle(title);
                song.setGenre(genre);
                song.setDuration(duration);
                song.setArtistId(loggedInArtist.getArtistId());

                artistService.uploadSong(song);
                break;


            case 4:
                if (!isLoggedIn) {
                    System.out.println(" Please login first.");
                    break;
                }

                sc.nextLine();
                System.out.print("Enter Album Title: ");
                String albumTitle = sc.nextLine();

                System.out.print("Enter Release Date: ");
                String releaseDate = sc.nextLine();

                Album album = new Album();
                album.setTitle(albumTitle);
                album.setArtistId(loggedInArtist.getArtistId());
                album.setReleaseDate(releaseDate);

                artistService.createAlbum(album);
                break;

            case 5:
                if (!isLoggedIn) {
                    System.out.println("Please login first.");
                    break;
                }

                System.out.print("Enter Song ID: ");
                int songId = sc.nextInt();
                sc.nextLine();

                System.out.print("New Title: ");
                String UStitle = sc.nextLine();

                System.out.print("New Genre: ");
                String USgenre = sc.nextLine();

                System.out.print("New Duration: ");
                int USduration = sc.nextInt();

                Song ussong = new Song();
                ussong.setSongId(songId);
                ussong.setTitle(UStitle);
                ussong.setGenre(USgenre);
                ussong.setDuration(USduration);

                try {
                    artistService.updateSong(ussong);
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }                
                break;

            case 6: {
                System.out.print("Enter Album ID: ");
                int albumId = sc.nextInt();
                sc.nextLine();

                System.out.print("New Album Title: ");
                String ualbumTitle = sc.nextLine();

                System.out.print("New Release Date: ");
                String ureleaseDate = sc.nextLine();

                Album ualbum = new Album();
                ualbum.setAlbumId(albumId);
                ualbum.setTitle(ualbumTitle);
                ualbum.setReleaseDate(ureleaseDate);

                artistService.updateAlbum(ualbum);
                break;
            }


            case 7: { 
                if (!isLoggedIn) {
                    System.out.println("Please login first.");
                    break;
                }

                System.out.print("Enter Song ID: ");
                int DsongId = sc.nextInt();

                try {
                    artistService.deleteSong(DsongId);
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }

            case 8: { 
                if (!isLoggedIn) {
                    System.out.println("Please login first.");
                    break;
                }

                System.out.print("Enter Album ID: ");
                int albumId = sc.nextInt();

                try {
                    artistService.deleteAlbum(albumId);
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            
            case 9: {
                if (!isLoggedIn) {
                    System.out.println("Please login first.");
                    break;
                }

                System.out.print("Enter Song ID: ");
                int CsongId = sc.nextInt();

                try {
                    artistService.viewSongPlayCount(CsongId);
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            
            case 10: {
                if (!isLoggedIn) {
                    System.out.println("Please login first.");
                    break;
                }

                System.out.print("Enter Song ID: ");
                int FsongId = sc.nextInt();

                try {
                    artistService.viewUsersWhoFavoritedSong(FsongId);
                } catch (RevPlayException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 11:
                return;


            	default:
            		System.out.println(" Invalid choice.");
            }
        }
    }
}
