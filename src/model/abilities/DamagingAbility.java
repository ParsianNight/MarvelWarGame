package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.effects.Shield;
import model.world.Champion;
import model.world.Condition;
import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int manaCost, int baseCooldown, int castRange, AreaOfEffect castArea,
			int actionsRequired, int damageAmount) {
		super(name, manaCost, baseCooldown, castRange, castArea, actionsRequired);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for (Damageable damageable : targets) {
			if (damageable instanceof Champion) {
				for (Effect effect : ((Champion) damageable).getAppliedEffects()) {
					if (effect instanceof Shield) {
						effect.remove((Champion) damageable);
						return;
					}
				}
			}
			
			damageable.setCurrentHP(damageable.getCurrentHP() - this.damageAmount);
			if(damageable.getCurrentHP()<=0) {
				if(damageable instanceof Champion) {
					
				((Champion) damageable).setCondition(Condition.KNOCKEDOUT);}
				else {
					damageable=null;
				}
				
			}
		}
		
	}

}
