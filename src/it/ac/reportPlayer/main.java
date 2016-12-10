package it.ac.reportPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class main extends JavaPlugin implements Listener
{
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("##########################");
		getLogger().info("# ReportPlayer è partito #");
		getLogger().info("##########################");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(cmd.getName().equals("segnala")&&sender.hasPermission("report.send")&&sender instanceof Player)
		{
			Player player = (Player) sender;
			String msg="";
			if(args.length==1)
			{
				msg=ChatColor.GOLD+player.getName()+org.bukkit.ChatColor.RED+" ha segnalato "+org.bukkit.ChatColor.GOLD+Bukkit.getPlayer(args[0]).getName()+org.bukkit.ChatColor.RED;
			}
			else if(args.length>1)
			{
				msg=ChatColor.GOLD+player.getName()+org.bukkit.ChatColor.RED+" ha segnalato "+org.bukkit.ChatColor.GOLD+Bukkit.getPlayer(args[0]).getName()+org.bukkit.ChatColor.RED+".\nMotivo:  "+ChatColor.YELLOW;
				for(int i=1; i<args.length; i++)
				{
					msg=msg+" "+args[i];
				}
			}
			else 
			{
				player.sendMessage(ChatColor.RED+"/segnala"+ChatColor.GOLD+" <player> <motivo>");
				return true;
			}
			Bukkit.broadcast(msg, "report.receive");
			getLogger().info(msg);
			player.sendMessage(org.bukkit.ChatColor.RED+"Hai segnalato "+org.bukkit.ChatColor.GOLD + Bukkit.getPlayer(args[0]).getName()+"");
			return true;
		}
		return false;
	}
	
}
