import { Strategy } from 'src/app/enums/startegy.enum';


export interface LeaveMessage {
  Strategy: Strategy;
  playerId: number;
}
