import { User } from './User'; 
import { News } from './News'; 
import { Decision } from './Decision'; 
import { Strategy } from './Strategy';

export class Scenario {
  id!: number; 
  title!: string; 
  description?: string; 
  startingDate?: Date; 
  endingDate?: Date; 
  historicalOutcome?: string; 
  asset?: string; 
  correctStrategy?: string; 

  
  users: User[] = [];
  news: News[] = []; 
  decisions: Decision[] = []; 
  strategies: Strategy[] = []; 
  constructor(init?: Partial<Scenario>) {
    Object.assign(this, init);
  }
}
