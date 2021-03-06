SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[yq_user]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[yq_user](
	[uaccount] [int] NOT NULL,
	[upassword] [nvarchar](20) NOT NULL,
	[unick] [nvarchar](20) NOT NULL,
	[uavatar] [int] NULL,
	[utrends] [nvarchar](50) NULL,
	[usex] [nvarchar](10) NULL,
	[uage] [int] NULL,
	[ulev] [int] NULL,
	[uisonline] [int] NULL,
	[utime] [nvarchar](30) NULL
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[yq_group]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[yq_group](
	[gaccount] [int] NOT NULL,
	[gnick] [nvarchar](50) NOT NULL,
	[gtrends] [nvarchar](50) NULL,
	[gmanager] [int] NOT NULL,
	[gcount] [int] NOT NULL
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[yq_group_member]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[yq_group_member](
	[gaccount] [int] NOT NULL,
	[gmember] [int] NOT NULL
) ON [PRIMARY]
END
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[yq_buddy]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[yq_buddy](
	[baccount] [int] NOT NULL,
	[bbuddy] [int] NOT NULL
) ON [PRIMARY]
END
