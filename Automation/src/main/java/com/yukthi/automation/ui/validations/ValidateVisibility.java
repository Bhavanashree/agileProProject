package com.yukthi.automation.ui.validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.yukthi.automation.AbstractValidation;
import com.yukthi.automation.AutomationContext;
import com.yukthi.automation.Executable;
import com.yukthi.automation.IExecutionLogger;
import com.yukthi.automation.ui.common.AutomationUtils;
import com.yukthi.utils.exceptions.InvalidStateException;

/**
 * Waits for locator to be part of the page and is visible.
 * 
 * @author akiran
 */
@Executable("validateVisibility")
public class ValidateVisibility extends AbstractValidation
{
	/**
	 * The logger.
	 */
	private static Logger logger = LogManager.getLogger(ValidateVisibility.class);

	/**
	 * locator to wait for.
	 */
	private String locator;

	/**
	 * If true, this step waits for element with specified locator gets removed
	 * or hidden.
	 */
	private boolean hidden = false;

	/**
	 * Message expected in the target element.
	 */
	private String message = null;

	/**
	 * Execute.
	 *
	 * @param context
	 *            the context
	 * @param exeLogger
	 *            the exe logger
	 * @return true, if successful
	 */
	@Override
	public boolean validate(AutomationContext context, IExecutionLogger exeLogger)
	{
		logger.trace("Waiting for element: {}", locator);

		AutomationUtils.validateWithWait(() -> {
			WebElement element = AutomationUtils.findElement(context, null, locator);

			if(hidden)
			{
				return (element == null || !element.isDisplayed());
			}

			return (element != null && element.isDisplayed());
		} , AutomationUtils.FIVE_SECONDS, "Waiting for element: " + locator, new InvalidStateException("Failed to find element - " + locator));

		if(message != null)
		{
			WebElement element = AutomationUtils.findElement(context, null, locator);
			String actualMessage = element.getText().trim();

			if(actualMessage == null || !actualMessage.contains(message))
			{
				exeLogger.error("Expected message '{}' is not matching with actual message '{}' for locator - {}", message, actualMessage, locator);
				return false;
			}
		}

		return true;
	}

	/**
	 * Gets the locator to wait for.
	 *
	 * @return the locator to wait for
	 */
	public String getLocator()
	{
		return locator;
	}

	/**
	 * Sets the locator to wait for.
	 *
	 * @param locator
	 *            the new locator to wait for
	 */
	public void setLocator(String locator)
	{
		this.locator = locator;
	}

	/**
	 * Checks if is if true, this step waits for element with specified locator
	 * gets removed or hidden.
	 *
	 * @return the if true, this step waits for element with specified locator
	 *         gets removed or hidden
	 */
	public boolean isHidden()
	{
		return hidden;
	}

	/**
	 * Sets the if true, this step waits for element with specified locator gets
	 * removed or hidden.
	 *
	 * @param hidden
	 *            the new if true, this step waits for element with specified
	 *            locator gets removed or hidden
	 */
	public void setHidden(boolean hidden)
	{
		this.hidden = hidden;
	}

	/**
	 * Gets the message expected in the target element.
	 *
	 * @return the message expected in the target element
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Sets the message expected in the target element.
	 *
	 * @param message
	 *            the new message expected in the target element
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String comma = ",";
		StringBuilder builder = new StringBuilder(super.toString());
		builder.append("[");

		builder.append("Locator: ").append(locator);
		builder.append(comma).append("Hidden: ").append(hidden);
		builder.append(comma).append("Message: ").append(message);

		builder.append("]");
		return builder.toString();
	}
}
