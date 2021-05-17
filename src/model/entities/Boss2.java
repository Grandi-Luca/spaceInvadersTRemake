package model.entities;

import model.entitiesutil.Enemy;
import model.entitiesutil.EntityDirections;
import model.entitiesutil.GenericEntityType;
import model.entitiesutil.bossutil.BossState;
import model.entitiesutil.typeentities.GenericEntity;
import model.physics.EntityCollision.EdgeCollision;
import model.physics.EntityMovementImpl;

/**
 * {@link Enemy} boss with a lot of life but that moves slowly
 */
public class Boss2 extends Enemy{

	private final int INITIAL_WIDTH = 0;
	private final int INITIAL_HEIGHT = 0;
	private final double INITIAL_MU_X = 0;
	private final double INITIAL_MU_Y = 0;
	private final int HITS_2ND_PHASE = 0;
	private final int MAX_HITS = 0;

	private boolean isAlreadyUpset;
	private BossState state;

	/**
	 * {@link Enemy} boss with a lot of life but that moves slowly
	 * 
	 * @param x is the initial x coordinate
	 * @param y is the initial y coordinate
	 */
	public Boss2(int x, int y) {
		this.create(SpecificEntityType.BOSS_2, x, y, this.INITIAL_WIDTH, this.INITIAL_HEIGHT, this.INITIAL_MU_X, this.INITIAL_MU_Y,
				this.MAX_HITS, EntityDirections.DOWN, 
				new EntityMovementImpl());
		this.state = BossState.NORMAL;
		this.isAlreadyUpset = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void changeDirection() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateEntityPosition() {
		this.changeState();
		if(this.state.equals(BossState.UPSET)) {
			this.getMovementMenager().moveDown(this);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		/*this.model.getNewEntitiesLevel().add(new MultiDirectionsEnemyBullet(
				new Pair<>(this.getX() + this.getWidth()/4 - 1, this.getY() + this.getHeight()), 
				EntityType.BOSS_2_BULLET));
		this.model.getNewEntitiesLevel().add(new MultiDirectionsEnemyBullet(
				new Pair<>(this.getX() + this.getWidth()* 3/4 - 1,this.getY() + this.getHeight()), 
				EntityType.BOSS_2_BULLET));*/
	}

	/**
	 * Change the state of the boss after it took too many hits 
	 */
	private void changeState() {
		if(this.getHits() >= this.HITS_2ND_PHASE && !isAlreadyUpset) {
			this.state = BossState.UPSET;
			this.isAlreadyUpset = true;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEntity(GenericEntity entity) {
		if(entity.getEntityType().equals(SpecificEntityType.PLAYER_1_BULLET) && this.isAlive()) {
			this.incHit();
		}
		if(entity.getEntityType().getGenericType().equals(GenericEntityType.PLAYER)) {
			//this.model.processGameOver();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAfterCollisionWithEdge(EdgeCollision edge) {
		if(edge.equals(EdgeCollision.DOWN)) {
			//this.model.processGameOver();
		}
	}
}
