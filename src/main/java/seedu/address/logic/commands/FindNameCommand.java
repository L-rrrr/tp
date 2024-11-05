package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindNameCommand extends Command {

    public static final String COMMAND_WORD = "fn";

    public static final String MESSAGE_USAGE = FindCommand.COMMAND_WORD + " " + PREFIX_NAME
            + " or " + COMMAND_WORD
            + ": Finds all persons whose names contain all of "
            + "the prefix of the specified NAME and displays them as a list with index numbers.\n"
            + "Parameters: NAME (String & must be non-empty)\n"
            + "Example:\n"
            + "- " + COMMAND_WORD + " Alice\n"
            + "- " + FindCommand.COMMAND_WORD + " " + PREFIX_NAME + "Alice\n"
            + "Additional Info: \n"
            + "- NAME is case-insensitive.\n"
            + "- It should contain letters, spaces, parenthesis or slashes only.\n"
            + "- They cannot be empty or have only spaces.";

    private final NameContainsKeywordsPredicate predicate;

    public FindNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                Messages.getMessagePersonsListedOverview(model.getDisplayPersons().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindNameCommand)) {
            return false;
        }

        FindNameCommand otherFindNameCommand = (FindNameCommand) other;
        return predicate.equals(otherFindNameCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
