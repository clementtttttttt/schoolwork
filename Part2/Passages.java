/**
 * Enum representing different passages
 * Each passage has a user-friendly name displayed in the UI.
 */
public enum Passages
{
    HAMNET("Hamnet", "QUEEN MARGARET:\nA little joy enjoys the queen thereof;\nFor I am she, and altogether joyless.\nI can no longer hold me patient.\nHear me, you wrangling pirates, that fall out\nIn sharing that which you have pill'd from me!\nWhich of you trembles not that looks on me?\nIf not, that, I being queen, you bow like subjects,\nYet that, by you deposed, you quake like rebels?\nO gentle villain, do not turn away!\n\nGLOUCESTER:\nFoul wrinkled witch, what makest thou in my sight?\n\nQUEEN MARGARET:\nBut repetition of what thou hast marr'd;\nThat will I make before I let thee go.\n\nGLOUCESTER:\nWert thou not banished on pain of death?\n\nQUEEN MARGARET:\nI was; but I do find more pain in banishment\nThan death can yield me here by my abode.\nA husband and a son thou owest to me;\nAnd thou a kingdom; all of you allegiance:\nThe sorrow that I have, by right is yours,\nAnd all the pleasures you usurp are mine.\n\nGLOUCESTER:\nThe curse my noble father laid on thee,\nWhen thou didst crown his warlike brows with paper\nAnd with thy scorns drew'st rivers from his eyes,\nAnd then, to dry them, gavest the duke a clout\nSteep'd in the faultless blood of pretty Rutland--\nHis curses, then from bitterness of soul\nDenounced against thee, are all fall'n upon thee;\nAnd God, not we, hath plagued thy bloody deed."),
    BEE_MOVIE("Bee movie","According to all known laws of aviation, there is no way a bee should be able to fly.\nIts wings are too small to get its fat little body off the ground.\nThe bee, of course, flies anyway because bees don't care what humans think is impossible.\nYellow, black. Yellow, black. Yellow, black. Yellow, black.\nOoh, black and yellow!\nLet's shake it up a little.\nBarry! Breakfast is ready!\nComing!\nHang on a second.\nHello?\nBarry?\nAdam?\nCan you believe this is happening?\nI can't.\nI'll pick you up.\nLooking sharp.\nUse the stairs, Your father paid good money for those.\nSorry. I'm excited.\nHere's the graduate.\nWe're very proud of you, son.\nA perfect report card, all B's."),
    C_PROG("C program", "#include <nonsense.h>\n#include <lies.h>\n#include <spyware.h> /* Microsoft Network Connectivity library */\n#include <process.h> /* For the court of law */\n\n#define say(x) lie(x)\n#define computeruser ALL_WANT_TO_BUY_OUR_BUGWARE\n#define next_year soon\n#define the_product_is_ready_to_ship   another_beta_version\n\nvoid main()\n{\n  if (latest_window_version>one_month_old)\n  {\n    if (there_are_still_bugs)\n      market(bugfix);\n    if (sales_drop_below_certain_point)\n      raise(RUMOURS_ABOUT_A_NEW_BUGLESS_VERSION);\n  }\n  while(everyone_chats_about_new_version)\n  {\n    make_false_promise(it_will_be_multitasking); /* Standard Call, in\n                                                    lie.h */\n    if (rumours_grow_wilder)\n      make_false_promise(it_will_be_plug_n_play);\n    if (rumours_grow_even_wilder)\n    {\n      market_time=ripe;\n      say(\"It will be ready in one month);\n      order(programmers, stop_fixing_bugs_in_old_version);\n      order(programmers, start_brainstorm_about_new_version);\n      order(marketingstaff, permission_to_spread_nonsense);\n      vapourware=TRUE;\n      break;\n     }\n  }\n  switch (nasty_questions_of_the_worldpress)"),
    CUSTOM("Custom","");

	String passage, displayName;
	


    /**
     * Constructor for Passage enum.
     *
     * @param displayName the user-friendly name to display in the UI
     * @param passage text
     */
    Passages(String displayName, String p)
    {
        this.displayName = displayName;
        passage = p;
    }
    
    String getPassage(){
		return passage;
	}


    /**
     * Returns the user-friendly name of this typing style.
     *
     * @return the display name
     */
    @Override
    public String toString()
    {
        return displayName;
    }
}
	
