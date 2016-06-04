package me.mittim.adminmsg;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener {
	
	private File file12 = new File("plugins/AMSG", "msg.yml");
	private FileConfiguration cfg12 = YamlConfiguration.loadConfiguration(file12);
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage("§c---------AdminMSG---------");
		Bukkit.getConsoleSender().sendMessage("§bCoded by: §c" + this.getDescription().getAuthors());
		Bukkit.getConsoleSender().sendMessage("§bVersion: §c" + this.getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage("§b/amsg fuer die Befehle!");
		Bukkit.getConsoleSender().sendMessage("§c---------AdminMSG---------");
	}
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§c---------AdminMSG---------");
		Bukkit.getConsoleSender().sendMessage("§cGod bye!");
		Bukkit.getConsoleSender().sendMessage("§c---------AdminMSG---------");
	}
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("amsg")){
			p.sendMessage("§c---------AdminMSG---------");
			p.sendMessage("§c/messsage <true/false> | aktiviert/deaktiviert das sehen der gesenden Messages!");
			p.sendMessage("§c/msg <Player> <Message>");
			p.sendMessage("§c---------AdminMSG---------");
		}
		if(cmd.getName().equalsIgnoreCase("msg")){
			if(args.length >= 2){
				String message = "";
				Player r = Bukkit.getPlayer(args[0]);
				if(r != null){
					for(int i = 1; i < args.length; i++){
					message = message + args[i] + " ";
					
					}
					p.sendMessage("§c[Me --> " + r.getName() + "] §b" + message);
					r.sendMessage("§c[" + p.getName() + " --> Me] §b" + message);
					Bukkit.getConsoleSender().sendMessage("§c[" + p.getName() + " --> "+ r.getName() +"] §b" + message);
					
						if(cfg12.getString(p.getName()) == "true" && p.isOp()){
						
							p.sendMessage("§c[" + p.getName() + " --> "+ r.getName() +"] §b" + message);
						}
						
					
				}else{
					p.sendMessage("§cThis Player is not online");
					return true;
				}
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("message")){
		if(p.hasPermission("amsg.message")){
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("false")){
					cfg12.set(p.getName(), "false");
					p.sendMessage("§cThe private message(msg) are no longer sent to you");
					try {
						cfg12.save(file12);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}else if(args[0].equalsIgnoreCase("true")){
					cfg12.set(p.getName(), "true");
					p.sendMessage("§cThe private message(msg) will now sent to you");
					try {
						cfg12.save(file12);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			
			
		}else{
			p.sendMessage("§cYou are not allowed to do that!");
		}
		}
		return true;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission("amsg.message")){
			cfg12.set(p.getName(), "false");
		}
	}

}
