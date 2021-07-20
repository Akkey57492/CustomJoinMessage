package message.custom.customjoinmessage;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinMessage extends JavaPlugin implements Listener {

    @Override
    public void onLoad() {
        getLogger().info("§bCustomJoinMessageがロードされました。");
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        getLogger().info("§aCustomJoinMessageが有効になりました。");
    }

    @Override
    public void onDisable() {
        getLogger().info("§cCustomJoinMessageが無効になりました。");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("msgreload")) {
            getLogger().warning("§eリロードします。");
            reloadConfig();
            getLogger().info("§aリロードが完了しました。");
            return true;
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = getConfig();
        String join = config.getString("Messages.Join.Message");
        String color = config.getString("Messages.Join.Color");
        String ccode = null;
        if(color != null) {
            switch (color) {
                case "green":
                    ccode = "§a";
                    break;
                case "yellow":
                    ccode = "§e";
                    break;
                case "red":
                    ccode = "§c";
                    break;
                default:
                    getLogger().warning("yml設定を確認してください。");
                    ccode = "§e";
                    break;
            }
        } else {
            event.setJoinMessage("§cymlファイルを確認してください。");
        }
        if(join != null) {
            String message = join.replace("{player}", player.getName());
            event.setJoinMessage(ccode + message);
        } else {
            event.setJoinMessage("§cymlファイルを確認してください。");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        FileConfiguration config = getConfig();
        String join = config.getString("Messages.Leave.Message");
        String color = config.getString("Messages.Leave.Color");
        String ccode = null;
        if(color != null) {
            switch (color) {
                case "green":
                    ccode = "§a";
                    break;
                case "yellow":
                    ccode = "§e";
                    break;
                case "red":
                    ccode = "§c";
                    break;
                default:
                    getLogger().warning("yml設定を確認してください。");
                    ccode = "§e";
                    break;
            }
        } else {
            event.setQuitMessage("§cymlファイルを確認してください。");
        }
        if(join != null) {
            String message = join.replace("{player}", player.getName());
            event.setQuitMessage(ccode + message);
        } else {
            event.setQuitMessage("§cymlファイルを確認してください。");
        }
    }
}
