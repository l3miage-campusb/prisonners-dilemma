import { Strategy } from 'src/app/enums/startegy.enum';


export interface LeaveMessage {
  strategy: Strategy;
  playerId: number;
}
