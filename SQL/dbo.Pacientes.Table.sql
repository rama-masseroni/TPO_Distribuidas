USE [CRUCIS]
GO
/****** Object:  Table [dbo].[Pacientes]    Script Date: 28/05/2020 18:01:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pacientes](
	[codigoUsr] [int] NOT NULL,
	[pagoAlDia] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigoUsr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[Pacientes]  WITH CHECK ADD  CONSTRAINT [FK_PacienteCodUsr_Usuarios] FOREIGN KEY([codigoUsr])
REFERENCES [dbo].[Usuarios] ([codigo])
GO
ALTER TABLE [dbo].[Pacientes] CHECK CONSTRAINT [FK_PacienteCodUsr_Usuarios]
GO
