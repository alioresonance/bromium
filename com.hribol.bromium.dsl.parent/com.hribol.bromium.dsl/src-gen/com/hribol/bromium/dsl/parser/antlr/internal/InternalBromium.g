/*
 * generated by Xtext 2.13.0
 */
grammar InternalBromium;

options {
	superClass=AbstractInternalAntlrParser;
}

@lexer::header {
package com.hribol.bromium.dsl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package com.hribol.bromium.dsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.hribol.bromium.dsl.services.BromiumGrammarAccess;

}

@parser::members {

 	private BromiumGrammarAccess grammarAccess;

    public InternalBromiumParser(TokenStream input, BromiumGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }

    @Override
    protected String getFirstRuleName() {
    	return "Model";
   	}

   	@Override
   	protected BromiumGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}

}

@rulecatch {
    catch (RecognitionException re) {
        recover(input,re);
        appendSkippedTokens();
    }
}

// Entry rule entryRuleModel
entryRuleModel returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getModelRule()); }
	iv_ruleModel=ruleModel
	{ $current=$iv_ruleModel.current; }
	EOF;

// Rule Model
ruleModel returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='name'
		{
			newLeafNode(otherlv_0, grammarAccess.getModelAccess().getNameKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_STRING
				{
					newLeafNode(lv_name_1_0, grammarAccess.getModelAccess().getNameSTRINGTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getModelRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		otherlv_2='version'
		{
			newLeafNode(otherlv_2, grammarAccess.getModelAccess().getVersionKeyword_2());
		}
		(
			(
				lv_version_3_0=RULE_STRING
				{
					newLeafNode(lv_version_3_0, grammarAccess.getModelAccess().getVersionSTRINGTerminalRuleCall_3_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getModelRule());
					}
					setWithLastConsumed(
						$current,
						"version",
						lv_version_3_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		(
			otherlv_4='from'
			{
				newLeafNode(otherlv_4, grammarAccess.getModelAccess().getFromKeyword_4_0());
			}
			(
				(
					{
						newCompositeNode(grammarAccess.getModelAccess().getBaseVersionVersionParserRuleCall_4_1_0());
					}
					lv_baseVersion_5_0=ruleVersion
					{
						if ($current==null) {
							$current = createModelElementForParent(grammarAccess.getModelRule());
						}
						set(
							$current,
							"baseVersion",
							lv_baseVersion_5_0,
							"com.hribol.bromium.dsl.Bromium.Version");
						afterParserOrEnumRuleCall();
					}
				)
			)
		)?
		otherlv_6='actions'
		{
			newLeafNode(otherlv_6, grammarAccess.getModelAccess().getActionsKeyword_5());
		}
		otherlv_7='{'
		{
			newLeafNode(otherlv_7, grammarAccess.getModelAccess().getLeftCurlyBracketKeyword_6());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getModelAccess().getActionsApplicationActionParserRuleCall_7_0());
				}
				lv_actions_8_0=ruleApplicationAction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getModelRule());
					}
					add(
						$current,
						"actions",
						lv_actions_8_0,
						"com.hribol.bromium.dsl.Bromium.ApplicationAction");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		otherlv_9='}'
		{
			newLeafNode(otherlv_9, grammarAccess.getModelAccess().getRightCurlyBracketKeyword_8());
		}
	)
;

// Entry rule entryRuleApplicationAction
entryRuleApplicationAction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getApplicationActionRule()); }
	iv_ruleApplicationAction=ruleApplicationAction
	{ $current=$iv_ruleApplicationAction.current; }
	EOF;

// Rule ApplicationAction
ruleApplicationAction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='id'
		{
			newLeafNode(otherlv_0, grammarAccess.getApplicationActionAccess().getIdKeyword_0());
		}
		(
			(
				lv_name_1_0=RULE_ID
				{
					newLeafNode(lv_name_1_0, grammarAccess.getApplicationActionAccess().getNameIDTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getApplicationActionRule());
					}
					setWithLastConsumed(
						$current,
						"name",
						lv_name_1_0,
						"org.eclipse.xtext.common.Terminals.ID");
				}
			)
		)
		otherlv_2='syntax'
		{
			newLeafNode(otherlv_2, grammarAccess.getApplicationActionAccess().getSyntaxKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getApplicationActionAccess().getSyntaxDefinitionsSyntaxDefinitionParserRuleCall_3_0());
				}
				lv_syntaxDefinitions_3_0=ruleSyntaxDefinition
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getApplicationActionRule());
					}
					add(
						$current,
						"syntaxDefinitions",
						lv_syntaxDefinitions_3_0,
						"com.hribol.bromium.dsl.Bromium.SyntaxDefinition");
					afterParserOrEnumRuleCall();
				}
			)
		)*
		(
			(
				{
					newCompositeNode(grammarAccess.getApplicationActionAccess().getPreconditionPreconditionParserRuleCall_4_0());
				}
				lv_precondition_4_0=rulePrecondition
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getApplicationActionRule());
					}
					set(
						$current,
						"precondition",
						lv_precondition_4_0,
						"com.hribol.bromium.dsl.Bromium.Precondition");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getApplicationActionAccess().getWebDriverActionWebDriverActionParserRuleCall_5_0());
				}
				lv_webDriverAction_5_0=ruleWebDriverAction
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getApplicationActionRule());
					}
					set(
						$current,
						"webDriverAction",
						lv_webDriverAction_5_0,
						"com.hribol.bromium.dsl.Bromium.WebDriverAction");
					afterParserOrEnumRuleCall();
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getApplicationActionAccess().getPostconditionPostconditionParserRuleCall_6_0());
				}
				lv_postcondition_6_0=rulePostcondition
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getApplicationActionRule());
					}
					set(
						$current,
						"postcondition",
						lv_postcondition_6_0,
						"com.hribol.bromium.dsl.Bromium.Postcondition");
					afterParserOrEnumRuleCall();
				}
			)
		)?
		(
			(
				{
					newCompositeNode(grammarAccess.getApplicationActionAccess().getExpectHttpRequestExpectHttpRequestParserRuleCall_7_0());
				}
				lv_expectHttpRequest_7_0=ruleExpectHttpRequest
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getApplicationActionRule());
					}
					set(
						$current,
						"expectHttpRequest",
						lv_expectHttpRequest_7_0,
						"com.hribol.bromium.dsl.Bromium.ExpectHttpRequest");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleSyntaxDefinition
entryRuleSyntaxDefinition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getSyntaxDefinitionRule()); }
	iv_ruleSyntaxDefinition=ruleSyntaxDefinition
	{ $current=$iv_ruleSyntaxDefinition.current; }
	EOF;

// Rule SyntaxDefinition
ruleSyntaxDefinition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getSyntaxDefinitionAccess().getSyntaxDefinitionAction_0(),
					$current);
			}
		)
		(
			(
				lv_content_1_0=RULE_STRING
				{
					newLeafNode(lv_content_1_0, grammarAccess.getSyntaxDefinitionAccess().getContentSTRINGTerminalRuleCall_1_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getSyntaxDefinitionRule());
					}
					setWithLastConsumed(
						$current,
						"content",
						lv_content_1_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		(
			(
				{
					newCompositeNode(grammarAccess.getSyntaxDefinitionAccess().getParameterExposedParameterParserRuleCall_2_0());
				}
				lv_parameter_2_0=ruleExposedParameter
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getSyntaxDefinitionRule());
					}
					set(
						$current,
						"parameter",
						lv_parameter_2_0,
						"com.hribol.bromium.dsl.Bromium.ExposedParameter");
					afterParserOrEnumRuleCall();
				}
			)
		)?
	)
;

// Entry rule entryRuleWebDriverActionCondition
entryRuleWebDriverActionCondition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWebDriverActionConditionRule()); }
	iv_ruleWebDriverActionCondition=ruleWebDriverActionCondition
	{ $current=$iv_ruleWebDriverActionCondition.current; }
	EOF;

// Rule WebDriverActionCondition
ruleWebDriverActionCondition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	{
		newCompositeNode(grammarAccess.getWebDriverActionConditionAccess().getElementByCssToBePresentParserRuleCall());
	}
	this_ElementByCssToBePresent_0=ruleElementByCssToBePresent
	{
		$current = $this_ElementByCssToBePresent_0.current;
		afterParserOrEnumRuleCall();
	}
;

// Entry rule entryRuleWebDriverAction
entryRuleWebDriverAction returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getWebDriverActionRule()); }
	iv_ruleWebDriverAction=ruleWebDriverAction
	{ $current=$iv_ruleWebDriverAction.current; }
	EOF;

// Rule WebDriverAction
ruleWebDriverAction returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		{
			newCompositeNode(grammarAccess.getWebDriverActionAccess().getClickCssSelectorParserRuleCall_0());
		}
		this_ClickCssSelector_0=ruleClickCssSelector
		{
			$current = $this_ClickCssSelector_0.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getWebDriverActionAccess().getPageLoadParserRuleCall_1());
		}
		this_PageLoad_1=rulePageLoad
		{
			$current = $this_PageLoad_1.current;
			afterParserOrEnumRuleCall();
		}
		    |
		{
			newCompositeNode(grammarAccess.getWebDriverActionAccess().getTypeTextInElementFoundByCssSelectorParserRuleCall_2());
		}
		this_TypeTextInElementFoundByCssSelector_2=ruleTypeTextInElementFoundByCssSelector
		{
			$current = $this_TypeTextInElementFoundByCssSelector_2.current;
			afterParserOrEnumRuleCall();
		}
	)
;

// Entry rule entryRulePrecondition
entryRulePrecondition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPreconditionRule()); }
	iv_rulePrecondition=rulePrecondition
	{ $current=$iv_rulePrecondition.current; }
	EOF;

// Rule Precondition
rulePrecondition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='when'
		{
			newLeafNode(otherlv_0, grammarAccess.getPreconditionAccess().getWhenKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getPreconditionAccess().getActionWebDriverActionConditionParserRuleCall_1_0());
				}
				lv_action_1_0=ruleWebDriverActionCondition
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getPreconditionRule());
					}
					set(
						$current,
						"action",
						lv_action_1_0,
						"com.hribol.bromium.dsl.Bromium.WebDriverActionCondition");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRulePostcondition
entryRulePostcondition returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPostconditionRule()); }
	iv_rulePostcondition=rulePostcondition
	{ $current=$iv_rulePostcondition.current; }
	EOF;

// Rule Postcondition
rulePostcondition returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='then'
		{
			newLeafNode(otherlv_0, grammarAccess.getPostconditionAccess().getThenKeyword_0());
		}
		otherlv_1='make'
		{
			newLeafNode(otherlv_1, grammarAccess.getPostconditionAccess().getMakeKeyword_1());
		}
		otherlv_2='sure'
		{
			newLeafNode(otherlv_2, grammarAccess.getPostconditionAccess().getSureKeyword_2());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getPostconditionAccess().getPostconditionWebDriverActionConditionParserRuleCall_3_0());
				}
				lv_postcondition_3_0=ruleWebDriverActionCondition
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getPostconditionRule());
					}
					set(
						$current,
						"postcondition",
						lv_postcondition_3_0,
						"com.hribol.bromium.dsl.Bromium.WebDriverActionCondition");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleExpectHttpRequest
entryRuleExpectHttpRequest returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExpectHttpRequestRule()); }
	iv_ruleExpectHttpRequest=ruleExpectHttpRequest
	{ $current=$iv_ruleExpectHttpRequest.current; }
	EOF;

// Rule ExpectHttpRequest
ruleExpectHttpRequest returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			{
				$current = forceCreateModelElement(
					grammarAccess.getExpectHttpRequestAccess().getExpectHttpRequestAction_0(),
					$current);
			}
		)
		(
			otherlv_1='do'
			{
				newLeafNode(otherlv_1, grammarAccess.getExpectHttpRequestAccess().getDoKeyword_1_0());
			}
			(
				(
					lv_not_2_0='not'
					{
						newLeafNode(lv_not_2_0, grammarAccess.getExpectHttpRequestAccess().getNotNotKeyword_1_1_0());
					}
					{
						if ($current==null) {
							$current = createModelElement(grammarAccess.getExpectHttpRequestRule());
						}
						setWithLastConsumed($current, "not", lv_not_2_0, "not");
					}
				)
			)?
			otherlv_3='expect'
			{
				newLeafNode(otherlv_3, grammarAccess.getExpectHttpRequestAccess().getExpectKeyword_1_2());
			}
			otherlv_4='http'
			{
				newLeafNode(otherlv_4, grammarAccess.getExpectHttpRequestAccess().getHttpKeyword_1_3());
			}
			otherlv_5='request'
			{
				newLeafNode(otherlv_5, grammarAccess.getExpectHttpRequestAccess().getRequestKeyword_1_4());
			}
		)
	)
;

// Entry rule entryRuleElementByCssToBePresent
entryRuleElementByCssToBePresent returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getElementByCssToBePresentRule()); }
	iv_ruleElementByCssToBePresent=ruleElementByCssToBePresent
	{ $current=$iv_ruleElementByCssToBePresent.current; }
	EOF;

// Rule ElementByCssToBePresent
ruleElementByCssToBePresent returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='element'
		{
			newLeafNode(otherlv_0, grammarAccess.getElementByCssToBePresentAccess().getElementKeyword_0());
		}
		otherlv_1='with'
		{
			newLeafNode(otherlv_1, grammarAccess.getElementByCssToBePresentAccess().getWithKeyword_1());
		}
		otherlv_2='css'
		{
			newLeafNode(otherlv_2, grammarAccess.getElementByCssToBePresentAccess().getCssKeyword_2());
		}
		otherlv_3='selector'
		{
			newLeafNode(otherlv_3, grammarAccess.getElementByCssToBePresentAccess().getSelectorKeyword_3());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getElementByCssToBePresentAccess().getCssSelectorParameterValueParserRuleCall_4_0());
				}
				lv_cssSelector_4_0=ruleParameterValue
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getElementByCssToBePresentRule());
					}
					set(
						$current,
						"cssSelector",
						lv_cssSelector_4_0,
						"com.hribol.bromium.dsl.Bromium.ParameterValue");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_5='is'
		{
			newLeafNode(otherlv_5, grammarAccess.getElementByCssToBePresentAccess().getIsKeyword_5());
		}
		otherlv_6='present'
		{
			newLeafNode(otherlv_6, grammarAccess.getElementByCssToBePresentAccess().getPresentKeyword_6());
		}
	)
;

// Entry rule entryRuleClickCssSelector
entryRuleClickCssSelector returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getClickCssSelectorRule()); }
	iv_ruleClickCssSelector=ruleClickCssSelector
	{ $current=$iv_ruleClickCssSelector.current; }
	EOF;

// Rule ClickCssSelector
ruleClickCssSelector returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='click'
		{
			newLeafNode(otherlv_0, grammarAccess.getClickCssSelectorAccess().getClickKeyword_0());
		}
		otherlv_1='on'
		{
			newLeafNode(otherlv_1, grammarAccess.getClickCssSelectorAccess().getOnKeyword_1());
		}
		otherlv_2='element'
		{
			newLeafNode(otherlv_2, grammarAccess.getClickCssSelectorAccess().getElementKeyword_2());
		}
		otherlv_3='with'
		{
			newLeafNode(otherlv_3, grammarAccess.getClickCssSelectorAccess().getWithKeyword_3());
		}
		otherlv_4='css'
		{
			newLeafNode(otherlv_4, grammarAccess.getClickCssSelectorAccess().getCssKeyword_4());
		}
		otherlv_5='selector'
		{
			newLeafNode(otherlv_5, grammarAccess.getClickCssSelectorAccess().getSelectorKeyword_5());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getClickCssSelectorAccess().getCssSelectorParameterValueParserRuleCall_6_0());
				}
				lv_cssSelector_6_0=ruleParameterValue
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getClickCssSelectorRule());
					}
					set(
						$current,
						"cssSelector",
						lv_cssSelector_6_0,
						"com.hribol.bromium.dsl.Bromium.ParameterValue");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRulePageLoad
entryRulePageLoad returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getPageLoadRule()); }
	iv_rulePageLoad=rulePageLoad
	{ $current=$iv_rulePageLoad.current; }
	EOF;

// Rule PageLoad
rulePageLoad returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='load'
		{
			newLeafNode(otherlv_0, grammarAccess.getPageLoadAccess().getLoadKeyword_0());
		}
		otherlv_1='page'
		{
			newLeafNode(otherlv_1, grammarAccess.getPageLoadAccess().getPageKeyword_1());
		}
		(
			(
				lv_subpath_2_0=RULE_STRING
				{
					newLeafNode(lv_subpath_2_0, grammarAccess.getPageLoadAccess().getSubpathSTRINGTerminalRuleCall_2_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getPageLoadRule());
					}
					setWithLastConsumed(
						$current,
						"subpath",
						lv_subpath_2_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
	)
;

// Entry rule entryRuleTypeTextInElementFoundByCssSelector
entryRuleTypeTextInElementFoundByCssSelector returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getTypeTextInElementFoundByCssSelectorRule()); }
	iv_ruleTypeTextInElementFoundByCssSelector=ruleTypeTextInElementFoundByCssSelector
	{ $current=$iv_ruleTypeTextInElementFoundByCssSelector.current; }
	EOF;

// Rule TypeTextInElementFoundByCssSelector
ruleTypeTextInElementFoundByCssSelector returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		otherlv_0='type'
		{
			newLeafNode(otherlv_0, grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getTypeKeyword_0());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getTextParameterValueParserRuleCall_1_0());
				}
				lv_text_1_0=ruleParameterValue
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getTypeTextInElementFoundByCssSelectorRule());
					}
					set(
						$current,
						"text",
						lv_text_1_0,
						"com.hribol.bromium.dsl.Bromium.ParameterValue");
					afterParserOrEnumRuleCall();
				}
			)
		)
		otherlv_2='in'
		{
			newLeafNode(otherlv_2, grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getInKeyword_2());
		}
		otherlv_3='element'
		{
			newLeafNode(otherlv_3, grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getElementKeyword_3());
		}
		otherlv_4='with'
		{
			newLeafNode(otherlv_4, grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getWithKeyword_4());
		}
		otherlv_5='css'
		{
			newLeafNode(otherlv_5, grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getCssKeyword_5());
		}
		otherlv_6='selector'
		{
			newLeafNode(otherlv_6, grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getSelectorKeyword_6());
		}
		(
			(
				{
					newCompositeNode(grammarAccess.getTypeTextInElementFoundByCssSelectorAccess().getCssSelectorParameterValueParserRuleCall_7_0());
				}
				lv_cssSelector_7_0=ruleParameterValue
				{
					if ($current==null) {
						$current = createModelElementForParent(grammarAccess.getTypeTextInElementFoundByCssSelectorRule());
					}
					set(
						$current,
						"cssSelector",
						lv_cssSelector_7_0,
						"com.hribol.bromium.dsl.Bromium.ParameterValue");
					afterParserOrEnumRuleCall();
				}
			)
		)
	)
;

// Entry rule entryRuleParameterValue
entryRuleParameterValue returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getParameterValueRule()); }
	iv_ruleParameterValue=ruleParameterValue
	{ $current=$iv_ruleParameterValue.current; }
	EOF;

// Rule ParameterValue
ruleParameterValue returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			(
				lv_content_0_0=RULE_STRING
				{
					newLeafNode(lv_content_0_0, grammarAccess.getParameterValueAccess().getContentSTRINGTerminalRuleCall_0_0());
				}
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getParameterValueRule());
					}
					setWithLastConsumed(
						$current,
						"content",
						lv_content_0_0,
						"org.eclipse.xtext.common.Terminals.STRING");
				}
			)
		)
		    |
		(
			(
				{
					if ($current==null) {
						$current = createModelElement(grammarAccess.getParameterValueRule());
					}
				}
				otherlv_1=RULE_ID
				{
					newLeafNode(otherlv_1, grammarAccess.getParameterValueAccess().getExposedParameterExposedParameterCrossReference_1_0());
				}
			)
		)
	)
;

// Entry rule entryRuleExposedParameter
entryRuleExposedParameter returns [EObject current=null]:
	{ newCompositeNode(grammarAccess.getExposedParameterRule()); }
	iv_ruleExposedParameter=ruleExposedParameter
	{ $current=$iv_ruleExposedParameter.current; }
	EOF;

// Rule ExposedParameter
ruleExposedParameter returns [EObject current=null]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	(
		(
			lv_name_0_0=RULE_ID
			{
				newLeafNode(lv_name_0_0, grammarAccess.getExposedParameterAccess().getNameIDTerminalRuleCall_0());
			}
			{
				if ($current==null) {
					$current = createModelElement(grammarAccess.getExposedParameterRule());
				}
				setWithLastConsumed(
					$current,
					"name",
					lv_name_0_0,
					"org.eclipse.xtext.common.Terminals.ID");
			}
		)
	)
;

// Entry rule entryRuleVersion
entryRuleVersion returns [String current=null]:
	{ newCompositeNode(grammarAccess.getVersionRule()); }
	iv_ruleVersion=ruleVersion
	{ $current=$iv_ruleVersion.current.getText(); }
	EOF;

// Rule Version
ruleVersion returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()]
@init {
	enterRule();
}
@after {
	leaveRule();
}:
	this_STRING_0=RULE_STRING
	{
		$current.merge(this_STRING_0);
	}
	{
		newLeafNode(this_STRING_0, grammarAccess.getVersionAccess().getSTRINGTerminalRuleCall());
	}
;

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;
