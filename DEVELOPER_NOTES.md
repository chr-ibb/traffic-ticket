# Developer Notes

This file tracks significant adjustments I had to make to Cursor's code. 

## TicketType Enum Location

### Original Issue
- The `TicketType` enum was initially placed as a second public class in `Ticket.java`
- This violated Java's rule of only one public class per file
- The initial fix was to move the enum to its own file (`TicketType.java`)

### Developer Adjustment
- Moved the `TicketType` enum to be a nested enum inside the `Ticket` class
- The enum is now accessed as `Ticket.TicketType.PARKING` or `Ticket.TicketType.SPEEDING`
- This makes the relationship between `Ticket` and `TicketType` more explicit in the code 