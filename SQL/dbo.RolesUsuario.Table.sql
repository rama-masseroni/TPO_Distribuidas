USE [CRUCIS]
GO
/****** Object:  Table [dbo].[RolesUsuario]    Script Date: 28/05/2020 18:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RolesUsuario](
	[codigoUsr] [int] NOT NULL,
	[codigoRol] [tinyint] NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[RolesUsuario]  WITH CHECK ADD  CONSTRAINT [FK_RolUsuarioCodRol_Roles] FOREIGN KEY([codigoRol])
REFERENCES [dbo].[Roles] ([codigo])
GO
ALTER TABLE [dbo].[RolesUsuario] CHECK CONSTRAINT [FK_RolUsuarioCodRol_Roles]
GO
ALTER TABLE [dbo].[RolesUsuario]  WITH CHECK ADD  CONSTRAINT [FK_RolUsuarioCodUsr_Usuarios] FOREIGN KEY([codigoUsr])
REFERENCES [dbo].[Usuarios] ([codigo])
GO
ALTER TABLE [dbo].[RolesUsuario] CHECK CONSTRAINT [FK_RolUsuarioCodUsr_Usuarios]
GO
