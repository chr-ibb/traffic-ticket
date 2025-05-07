# Developer Notes

This file tracks significant adjustments made to the code by developers, particularly when the original solution was modified or improved.

## 2024-03-19: TicketType Enum Location

### Original Issue
- The `TicketType` enum was initially placed as a second public class in `Ticket.java`
- This violated Java's rule of only one public class per file
- The initial fix was to move the enum to its own file (`TicketType.java`)

### Developer Adjustment
- Moved the `TicketType` enum to be a nested enum inside the `Ticket` class
- This is a better solution because:
  - The enum is tightly coupled with the `Ticket` class
  - It's more encapsulated
  - It follows the principle of keeping related code together
  - It's a common pattern in Java when an enum is only used by one class
- The enum is now accessed as `Ticket.TicketType.PARKING` or `Ticket.TicketType.SPEEDING`
- This makes the relationship between `Ticket` and `TicketType` more explicit in the code 