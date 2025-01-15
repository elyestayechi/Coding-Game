import { Scenario } from './Scenario';

export class Strategy {
    id!: number; // Strategy ID
    name!: string; // Strategy name, required
    description?: string; // Optional description of the strategy (max length: 1000)
  
    // Relationships
    scenarios: Scenario[] = []; // Many-to-many relationship with scenarios
  
    constructor(init?: Partial<Strategy>) {
      Object.assign(this, init);
    }
  }
  