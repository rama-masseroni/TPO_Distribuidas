USE [CRUCIS]
GO
/****** Object:  Table [dbo].[Medicos]    Script Date: 28/05/2020 18:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Medicos](
	[codigoUsr] [int] NOT NULL,
	[codigoEsp] [smallint] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigoUsr] ASC,
	[codigoEsp] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Medicos]  WITH CHECK ADD  CONSTRAINT [FK_MedicoCodEsp_Especialidades] FOREIGN KEY([codigoEsp])
REFERENCES [dbo].[Especialidades] ([codigo])
GO
ALTER TABLE [dbo].[Medicos] CHECK CONSTRAINT [FK_MedicoCodEsp_Especialidades]
GO
ALTER TABLE [dbo].[Medicos]  WITH CHECK ADD  CONSTRAINT [FK_MedicoCodUsr_Usuarios] FOREIGN KEY([codigoUsr])
REFERENCES [dbo].[Usuarios] ([codigo])
GO
ALTER TABLE [dbo].[Medicos] CHECK CONSTRAINT [FK_MedicoCodUsr_Usuarios]
GO
