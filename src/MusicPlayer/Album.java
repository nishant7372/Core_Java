package MusicPlayer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Album {
    private List<Song> songList;

    private String name;

    public Album(String name) {
        this.songList = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addSong(String songName, double songDuration)
    {
        if(findSong(songName)==null)
        {
            return songList.add(new Song(songName,songDuration));
        }
        System.out.println("Song: " + songName + " is already in the album");
        return false;
    }

    public boolean removeSong(String songName)
    {
        Song existingSong = findSong(songName);
        if(existingSong!=null)
        {
            return songList.remove(existingSong);
        }
        System.out.println("Song: " + songName + " is not in the album");
        return false;
    }

    private Song findSong(String songName)
    {
        for (Song song : songList) {
            if (song.getName().equals(songName))
                return song;
        }
        return null;
    }

     public void showSongList()
     {
         for (Song song : songList) {
             System.out.println(song);
         }
     }

     public boolean addToPlayList(LinkedList<Song> playList,int trackNo)
     {
         int index=trackNo-1;
         if(index>=0&&index<songList.size())
         {
             if(!playList.contains(songList.get(index))) {
                 return playList.add(songList.get(index));
             }
             else {
                 System.out.println("Track No " + trackNo + " is already present in this playList");
                 return false;
             }
         }
         System.out.println("Track No " + trackNo + " is not present in this Album");
         return false;
     }
}
