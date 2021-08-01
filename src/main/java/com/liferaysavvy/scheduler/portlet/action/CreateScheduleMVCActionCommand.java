/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferaysavvy.scheduler.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferaysavvy.scheduler.constants.LiferaySchedulerExamplePortletKeys;
import com.liferaysavvy.scheduler.service.SchedulerServiceImpl;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Wilson S. Man
 * @author Thiago Moreira
 * @author Juan Fernández
 * @author Zsolt Berentey
 * @author Levente Hudák
 */

@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" + LiferaySchedulerExamplePortletKeys.LIFERAYSCHEDULEREXAMPLE,
			"mvc.command.name=/create/schedule"
	},
	service = MVCActionCommand.class
)
public class CreateScheduleMVCActionCommand extends BaseMVCActionCommand {

	
	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		String destinationName = ParamUtil.getString(actionRequest, "destination");
		String cron = ParamUtil.getString(actionRequest, "cron");
		String jobName = ParamUtil.getString(actionRequest, "jobname");
		String groupName = ParamUtil.getString(actionRequest, "groupname");
		String description = ParamUtil.getString(actionRequest, "description");
		
		_log.info("destination::"+destinationName);
		_log.info("cron::"+cron);
		_log.info("jobName:"+jobName);
		_log.info("groupName:"+groupName);
		_log.info("description:"+description);
		
		createDynamicScheduleJob(jobName, groupName, cron,destinationName,description);
		actionResponse.setRenderParameter("mvcRenderCommandName", "/view/create_schedule");
	}
	protected void createDynamicScheduleJob(String jobName, String groupName, String cron,String destinationName,String description)
		throws Exception {
		schedulerService.createSchedule(jobName, groupName, cron,destinationName,description);
	}

	
	

	private static final Log _log = LogFactoryUtil.getLog(
		CreateScheduleMVCActionCommand.class);
	@Reference
    private SchedulerServiceImpl schedulerService;
}