package me.puppyz.announcer;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class Main extends JavaPlugin {
  public int broadcastnumber = 0;
  public static Main plugin;
  
  public void Broadcasts()
  {
    int time = getConfig().getInt("Time");
    Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable()
    {
      public void run()
      {
        if (Main.this.broadcastnumber == 0)
        {
          int broadcasts = Main.this.getConfig().getStringList("Broadcasts").size();
          Main.this.broadcastnumber = broadcasts;
        }
        String header = Main.this.getConfig().getString("TopBorder");
        Bukkit.getServer().broadcastMessage(header);
        
        String message = (String)Main.this.getConfig().getStringList("Broadcasts").get(Main.this.broadcastnumber - 1);
        Bukkit.getServer().broadcastMessage(message);
        
        String footer = Main.this.getConfig().getString("BottomBorder");
        Bukkit.getServer().broadcastMessage(footer);
        
        Main.this.broadcastnumber -= 1;
      }
    }, 0L, 20 * time);
  }
  
  public void onEnable()
  {
    plugin = this;
    saveDefaultConfig();
    Broadcasts();
  }
}

        
        
 