package MusicPlayer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class App {
    private static AlbumStack albumStack = new AlbumStack();
    private static Scanner scan= new Scanner(System.in);
    private static LinkedList<Song> playList = new LinkedList<>();
    public static void main(String[] args) {
        boolean quit = false;
        availableActions();
        while(!quit) {
            System.out.println("Enter your choice(press 0 for available actions):");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 0 -> availableActions();
                case 1 -> addAlbum();
                case 2 -> addSong();
                case 3 -> removeSong();
                case 4 -> removeAlbum();
                case 5 -> viewSongList();
                case 6 -> addSongToPlayList();
                case 7 -> removeSongFromPlayList();
                case 8 -> viewPlayList();
                case 9 -> goToPlayer();
                case 10 -> quit = true;
            }
        }
    }

    private static void availableActions()
    {
        playList.add(new Song("Jannat Ve - Darshan Rawal",4.56));
        playList.add(new Song("Srivalli - Devi Sri Prasad",3.28));
        playList.add(new Song("Mehbooba - Ravi Basrur",2.34));
        playList.add(new Song("Falak Tu Garaj Tu - Ravi Basrur",3.44));
        playList.add(new Song("Kesariya - Arijit Singh",2.16));
        System.out.println("\nAvailable Actions: \npress-");
        System.out.println("0 - to view available actions");
        System.out.println("1 - to add an Album");
        System.out.println("2 - to add a song");
        System.out.println("3 - to delete a song");
        System.out.println("4 - to delete an Album");
        System.out.println("5 - to view Song List");
        System.out.println("6 - to add song to playList");
        System.out.println("7 - to remove song from playList");
        System.out.println("8 - to view Play List");
        System.out.println("9 - go to Player");
        System.out.println("10 - to quit");
    }

    private static void addSong()
    {
        System.out.println("Enter Album Name:");
        String albumName = scan.nextLine();
        System.out.println("Enter Song Name:");
        String songName = scan.nextLine();
        System.out.println("Enter Song Duration:");
        double songDuration = scan.nextDouble();
        scan.nextLine();
        if(albumStack.addSong(albumName,songName,songDuration))
            System.out.println("Song " + songName + " added successfully to the album " + albumName);
    }

    private static void removeSong()
    {
        System.out.println("Enter Album Name:");
        String albumName = scan.nextLine();
        System.out.println("Enter Song Name:");
        String songName = scan.nextLine();
        if(albumStack.removeSong(albumName,songName))
            System.out.println("Song " + songName + " removed successfully from the album " + albumName);
    }

    private static void addAlbum()
    {
        System.out.println("Enter Album Name:");
        String albumName = scan.nextLine();
        if(albumStack.addAlbum(albumName))
            System.out.println("Album " + albumName + " added successfully");
    }

    private static void removeAlbum()
    {
        System.out.println("Enter Album Name:");
        String albumName = scan.nextLine();
        if(albumStack.removeAlbum(albumName))
            System.out.println("Album " + albumName + " removed successfully");
    }

    private static void viewSongList()
    {
        System.out.println("Enter the Album Name:");
        albumStack.showSongList(scan.nextLine());
    }

    private static void addSongToPlayList()
    {
        System.out.println("Enter Album Name:");
        String albumName = scan.nextLine();
        System.out.println("Enter track No.");
        int trackNo = scan.nextInt();
        scan.nextLine();
        if(albumStack.addToPlayList(albumName,playList,trackNo))
            System.out.println("TrackNo " + trackNo + " of the album " + albumName + " is added successfully to the playList");
    }

    private static void viewPlayList()
    {
        System.out.println("\n--------------------My PlayList----------------------\n");
        Iterator<Song> iter = playList.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }
        System.out.println("\n-----------------------------------------------------\n");
    }

    private static void removeSongFromPlayList()
    {
        System.out.println("Enter track no:");
        int trackNo = scan.nextInt();
        scan.nextLine();
        if(trackNo-1>=0&&trackNo<playList.size())
        {
            playList.remove(trackNo);
            System.out.println("Track No " + trackNo + " has been removed from the playList");
        }
        System.out.println("Track No " + trackNo + " is not in the playList");
    }

    private static void goToPlayer()
    {
        System.out.println("Welcome to the Music Player...");
        ListIterator<Song> playListIterator = playList.listIterator();
        if(playListIterator.hasNext())
          play(playListIterator.next());
        else {
            System.out.println("PlayList is Empty!!!");
            return;
        }
        boolean goBack = false;
        boolean forward=true;
        while(!goBack)
        {
            System.out.println("\n--------------------------\npress 0 - replay");
            System.out.println("press 1 - play next");
            System.out.println("press 2 - play previous");
            System.out.println("press 3 - exitPlayer\n--------------------------\n");
            System.out.println("Enter your choice:");
            int choice = scan.nextInt();
            scan.nextLine();
        switch(choice)
        {
            case 0 :
                if(forward)
                {
                if(playListIterator.hasPrevious()) {
                    replay(playListIterator.previous());
                    forward = false;
                }
                else{
                    System.out.println("We are at the start of the playList");
                }
                }
                else {
                    if(playListIterator.hasNext()) {
                        replay(playListIterator.next());
                        forward=true;
                    }
                    else {
                        System.out.println("We have reached the end of the playList");
                    }
                }
                break;

            case 1 : if(!forward) {
                if (playListIterator.hasNext())
                    playListIterator.next();
                forward=true;
            }
                     if(playListIterator.hasNext())
                     play(playListIterator.next());
                     else {
                         System.out.println("We have reached the end of the playList");
                         forward=false;
                     }
                     break;
            case 2:  if(forward)
                     {
                         if(playListIterator.hasPrevious())
                             playListIterator.previous();
                         forward=false;
                     }
                     if(playListIterator.hasPrevious())
                     play(playListIterator.previous());
                     else {
                         System.out.println("We are at the start of the playList");
                         forward=true;
                     }
                     break;
            case 3: goBack=true;
                    break;
        }
    }
    }
    private static void play(Song song)
    {
        System.out.println("Now Playing... " + song);
    }

    private static void replay(Song song)
    {
        System.out.println("Now Replaying... " + song);
    }
}
