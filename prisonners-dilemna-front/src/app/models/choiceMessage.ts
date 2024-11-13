import { Choice } from "../enums/choice.enum";

export interface ChoiceMessage {
  playerId: number;
  choice: Choice; // Limitez les choix possibles
}
