import { Decision } from './Decision';
import { Scenario } from './Scenario';

export class User {
    id!: number;
    username!: string; 
    balance: number = 10000.0; 
    totalProfitLoss: number = 0.0; 

  
    scenarios: Scenario[] = []; 
    decisions: Decision[] = []; 
  
    constructor(init?: Partial<User>) {
      Object.assign(this, init);
    }
  } 
  