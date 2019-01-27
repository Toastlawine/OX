package tl.ox.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tl.ox.enums.EN_State;
import tl.ox.main.Game;
import tl.ox.storage.ST_String;

public class CMD_start implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("ox.start")) {
				if (args.length == 0) {
					if (Game.getPs().containsKey(p)) {
						if (Game.getState() == EN_State.LOBBY) {
							if (Game.getMinP() == 1 || Game.getPs().size() > 1) {
								if (Game.getS() > 5) {
									Game.cancelTimer();
									Game.startTimer();
									Game.setS(5);
								} else
									p.sendMessage(ST_String.getPF() + "§cDas Spiel ist bereits kurz vor dem Beginn!");
							} else
								p.sendMessage(ST_String.getPF() + "§cEs müssen mindestens §b2§c Spieler da sein!");
						} else
							p.sendMessage(ST_String.getPF() + "Das Spiel startet gerade nicht!");
					} else
						p.sendMessage(ST_String.getPF() + "§cDu bist nicht im Spiel!");
				} else
					p.sendMessage(ST_String.getTooManyArgs());
			} else
				p.sendMessage(ST_String.getNoPerm());
		}

		return false;
	}

}
